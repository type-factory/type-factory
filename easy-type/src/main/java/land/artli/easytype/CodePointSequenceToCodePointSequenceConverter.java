package land.artli.easytype;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import land.artli.easytype.CodePointConversions.ConverterResults;

public class CodePointSequenceToCodePointSequenceConverter {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private final List<Conversion> conversions;

  private final RootNode rootNode = new RootNode();

  public CodePointSequenceToCodePointSequenceConverter(List<Conversion> conversions) {
    this.conversions = conversions;
    for (Conversion conversion : conversions) {
      rootNode.add(conversion);
    }
  }

  /**
   * Will return {@code true} if a conversion is required with the conversion information set into the {@code converterResults} object that was passed
   * in as an argument.
   *
   * @param currentCodePoint
   * @param currentIndex
   * @param converterResults
   * @return {@code true} if a conversion is required with the conversion information set into the {@code converterResults} object that was passed in
   * as an argument.
   */
  boolean codePointConversionRequired(final int currentCodePoint, final int currentIndex, final ConverterResultsImpl converterResults) {
    boolean result = false;
    Node foundNode = null;
    Node nodeInPlay = null;
    converterResults.indexesToFromConvertFrom.add(currentIndex);
    converterResults.nodesCurrentlyInPlay.add(rootNode);
    for (int i = converterResults.nodesCurrentlyInPlay.size() - 1; i >= 0; --i) {
      nodeInPlay = converterResults.nodesCurrentlyInPlay.get(i);
      foundNode = nodeInPlay.get(currentCodePoint);
      if (foundNode == null) {
        converterResults.indexesToFromConvertFrom.remove(i);
        converterResults.nodesCurrentlyInPlay.remove(i);
      } else {
        if (foundNode.hasToSequence()) {
          converterResults.convertFromIndex = converterResults.indexesToFromConvertFrom.get(i);
          converterResults.convertToCodePointSequence = foundNode.toSequence;
          if (foundNode.isLeafNode()) {
            converterResults.indexesToFromConvertFrom.remove(i);
            converterResults.nodesCurrentlyInPlay.remove(i);
          }
          result = true;
        }
        if (!foundNode.isLeafNode()) {
            converterResults.nodesCurrentlyInPlay.set(i, foundNode);
        }
      }
    }

    if (result) {
      for (int i = converterResults.nodesCurrentlyInPlay.size() - 1; i >= 0; --i) {
        if (converterResults.indexesToFromConvertFrom.get(i) != converterResults.convertFromIndex) {
          converterResults.indexesToFromConvertFrom.remove(i);
          converterResults.nodesCurrentlyInPlay.remove(i);
        }
      }
    }

    return result;
  }

//  private Node prepare(final List<Conversion> conversions) {
//    final Node rootNode = new Node(null);
//    for (Conversion conversion : conversions) {
//      for (int codePoint : conversion.fromSequence) {
//
//      }
//    }
//  }

  static class ConverterResultsImpl implements ConverterResults {

    private int convertFromIndex;
    private int[] convertToCodePointSequence;

    private final List<Integer> indexesToFromConvertFrom = new ArrayList<>();

    private final List<Node> nodesCurrentlyInPlay = new ArrayList<>();

    public int getConvertFromIndex() {
      return convertFromIndex;
    }

    public void setConvertFromIndex(int convertFromIndex) {
      this.convertFromIndex = convertFromIndex;
    }

    public int[] getConvertToCodePointSequence() {
      return convertToCodePointSequence;
    }

    public void setConvertToCodePointSequence(int[] convertToCodePointSequence) {
      this.convertToCodePointSequence = convertToCodePointSequence;
    }
  }

  /**
   * <pre>
   *   ┌─────────────────────────────────────────┐
   *   │ Node                                    │←─┐
   *   ├─────────────────────────────────────────┤  │ map of int codePoint keys to Node values
   *   │ nodesByCodePoint : Map‹codePoint, Node› │──┘
   *   │ toSequence       : int[]                │──────→ int[] codePoints
   *   └─────────────────────────────────────────┘
   * </pre>
   */
  private static class Node {

    public Node() {
      this(new int[0]);
    }

    public Node(final int[] toSequence) {
      this.toSequence = toSequence;
      this.nodesByCodePoint = new CodePointToNodeHashMap();
    }

    private int[] toSequence = new int[0];
    private final CodePointToNodeHashMap nodesByCodePoint;

    void put(final int codePoint, final Node node) {
      nodesByCodePoint.put(codePoint, node);
    }

    Node get(final int codePoint) {
      return nodesByCodePoint.get(codePoint);
    }

    Set<Integer> codePoints() {
      return nodesByCodePoint.keySet();
    }

    boolean hasToSequence() {
      return toSequence != null && toSequence.length > 0;
    }

    boolean isLeafNode() {
      return nodesByCodePoint.isEmpty();
    }
  }

  private static class RootNode extends Node {

    final List<Position> positions = new ArrayList<>();

    private static class Position {

      final int startCharIndex;
      Node node;

      public Position(int startCharIndex) {
        this.startCharIndex = startCharIndex;
      }
    }

    void add(final Conversion conversion) {
      Node currentNode = this;
      for (int i = 0; i < conversion.fromSequence.length; ++i) {
        int codePoint = conversion.fromSequence[i];
        Node nextNode = currentNode.get(codePoint);
        if (nextNode == null) {
          nextNode = new Node();
          currentNode.put(codePoint, nextNode);
        }
        currentNode = nextNode;
      }
      currentNode.toSequence = conversion.toSequence;
    }

    @Override
    public String toString() {
      final StringBuilder s = new StringBuilder();
      final Deque<Node> nodeStack = new ArrayDeque<>();
      final Deque<Integer> indexStack = new ArrayDeque<>();
      Node currentNode = this;
      Integer[] codePoints = currentNode.codePoints().toArray(new Integer[0]);
      int i = 0;
      int j = 0;
      while (i < codePoints.length) {
        Integer codePoint = codePoints[i];
        for (int k = 1; k < j; ++k) {
          s.append(' ');
        }
        if (i < codePoints.length - 1) {
          s.append('├');
        } else if (j > 0) {
          s.append('└');
        }
        s.appendCodePoint(codePoint);
        Node node = currentNode.get(codePoint);
        if (node != null) {
          if (node.hasToSequence()) {
            s.append(" —→ ");
            s.append(new String(node.toSequence, 0, node.toSequence.length));
            s.append(LINE_SEPARATOR);
          }
          if (!node.codePoints().isEmpty()) {
            ++j;
            s.append(LINE_SEPARATOR);
            nodeStack.push(currentNode);
            indexStack.push(i);
            currentNode = node;
            codePoints = currentNode.codePoints().toArray(new Integer[0]);
            i = 0;
          } else {
            do {
              currentNode = nodeStack.pop();
              codePoints = currentNode.codePoints().toArray(new Integer[0]);
              i = indexStack.pop();
              ++i;
              --j;
            } while (i >= codePoints.length && !indexStack.isEmpty());
          }
        }
      }
      return s.toString();
    }
  }

  private static class CodePointToNodeHashMap extends HashMap<Integer, Node> {

    public CodePointToNodeHashMap() {
      super(8);
    }
  }


  static class Conversion {

    Conversion(final int[] fromSequence, final int[] toSequence) {
      this.fromSequence = fromSequence;
      this.toSequence = toSequence;
    }

    private final int[] fromSequence;
    private final int[] toSequence;
  }

  public static void main(String... args) {
    final RootNode rootNode = new RootNode();
    rootNode.add(new Conversion("abcd".codePoints().toArray(), "xyz".codePoints().toArray()));
    rootNode.add(new Conversion("abef".codePoints().toArray(), "tuv".codePoints().toArray()));
    System.out.println(rootNode.toString());
  }
}
