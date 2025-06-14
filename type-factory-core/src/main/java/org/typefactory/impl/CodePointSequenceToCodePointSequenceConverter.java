/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory.impl;

import static org.typefactory.impl.Constants.SYSTEM_LINE_SEPARATOR;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.typefactory.TypeParser;

final class CodePointSequenceToCodePointSequenceConverter implements Converter {

  /**
   * The root tree-node for the n-ary tree.
   */
  private final RootTreeNode rootTreeNode;

  /**
   * Hash-map of Character categories to code-point arrays:
   */
  private final PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence;

  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  private final int maxConvertedLength;

  CodePointSequenceToCodePointSequenceConverter(
      final RootTreeNode rootTreeNode,
      final PrimitiveHashMapOfIntKeyToIntArrayValue categoryToCodePointSequence) {
    this.rootTreeNode = rootTreeNode;
    this.categoryToCodePointSequence = categoryToCodePointSequence;
    int max = 0;
    if (rootTreeNode != null && !rootTreeNode.isEmpty()) {
      max = Math.max(max, rootTreeNode.getMaxToSequenceLength());
    }
    if (categoryToCodePointSequence != null && !categoryToCodePointSequence.isEmpty()) {
      max = Math.max(max, categoryToCodePointSequence.getMaxValueArrayLength());
    }
    this.maxConvertedLength = max;

  }

  /**
   * Creates a {@link ConverterResults} object to be used for the life of a single {@link TypeParser} parse method.
   * <p>
   * The idea is to discourage object creation and encourage reuse of this one instance by passing it to the converter to receive the results into.
   * The alternative would have been for the converter method to instantiate and return a new object for the tuple every time it was called which
   * would be for every character in the parsed character sequence.
   *
   * @return a {@link ConverterResults} object to be used for the life of a single {@link TypeParser} parse method.
   */
  public ConverterResults createConverterResults() {
    return new ConverterResultsImpl();
  }

  public boolean isEmpty() {
    return (rootTreeNode == null || rootTreeNode.isEmpty())
           && (categoryToCodePointSequence == null || categoryToCodePointSequence.isEmpty());
  }
  
  /**
   * The maximum number of code-points that a single code-point could be converted to.
   */
  public int getMaxConvertedLength() {
    return maxConvertedLength;
  }

  /**
   * Will return {@code true} if a conversion is required with the conversion information set into the {@code converterResults} object that was passed
   * in as an argument.
   *
   * @param currentCodePoint the current code-point being parsed
   * @param currentIndex the current index of the code-point being parsed in the character sequence
   * @param converterResults the {@link ConverterResults} object that will receive the conversion information if a conversion is required.
   * @return {@code true} if a conversion is required with the conversion information set into the {@code converterResults} object that was passed in
   * as an argument.
   */
  public boolean codePointConversionIsRequired(final int currentCodePoint, final int currentIndex, final ConverterResults converterResults) {
    if (converterResults instanceof ConverterResultsImpl converterResultsImpl) {
      return codePointConversionIsRequired(currentCodePoint, currentIndex, converterResultsImpl);
    }
    throw new IllegalArgumentException("Invalid argument - 'converterResults' must be of type " + ConverterResultsImpl.class.getName());
  }

  private boolean codePointConversionIsRequired(final int currentCodePoint, final int currentIndex, final ConverterResultsImpl converterResults) {
    boolean result = false;
    TreeNode foundTreeNode = null;
    TreeNode treeNodeInPlay = null;
    converterResults.addTreeNodeInPlay(currentIndex, rootTreeNode);
    for (int i = converterResults.treeNodesInPlaySize() - 1; i >= 0; --i) {
      treeNodeInPlay = converterResults.getTreeNodeInPlay(i);
      foundTreeNode = treeNodeInPlay.get(currentCodePoint);
      if (foundTreeNode == null) {
        converterResults.removeTreeNodeInPlay(i);
      } else {
        if (foundTreeNode.hasToSequence()) {
          converterResults.convertFromIndex = converterResults.getConvertFromIndexForNodeInPlay(i);
          converterResults.convertToCodePointSequence = foundTreeNode.toCodePointSequence;
          if (foundTreeNode.isLeafNode()) {
            converterResults.removeTreeNodeInPlay(i);
          }
          result = true;
        }
        if (!foundTreeNode.isLeafNode()) {
          converterResults.updateTreeNodeInPlay(i, foundTreeNode);
        }
      }
    }

    if (result) {
      for (int i = converterResults.treeNodesInPlaySize() - 1; i >= 0; --i) {
        if (converterResults.getConvertFromIndexForNodeInPlay(i) > converterResults.convertFromIndex) {
          converterResults.removeTreeNodeInPlay(i);
        }
      }
    }

    if (!result && categoryToCodePointSequence != null) {
      final int [] toCodePoints = categoryToCodePointSequence.get(Character.getType(currentCodePoint));
      if (toCodePoints != null) {
        converterResults.convertFromIndex = currentIndex;
        converterResults.convertToCodePointSequence = toCodePoints;
        result = true;
      }
    }

    return result;
  }

  /**
   * <p>A reusable {@link ConverterResults} implementation that is to be used by the {@link TypeParser} to receive information about a code-point
   * sequence conversion when it is required.</p>
   *
   * <p>It is designed to be used when parsing a code-point sequence one code-point at a time as the {@link TypeParser} does.</p>
   *
   * <p>This {@link ConverterResults} instance is specific to the
   * {@link CodePointSequenceToCodePointSequenceConverter} because it maintains state about the current n-ary tree-nodes in play for the
   * {@link Converter} and the {@link TypeParser}.</p>
   */
  static class ConverterResultsImpl implements ConverterResults {

    private final List<TreeNode> treeNodeInPlay = new ArrayList<>();
    private final PrimitiveListOfInt convertFromIndexesForNodesInPlay = new PrimitiveListOfInt();
    private int convertFromIndex;
    private int[] convertToCodePointSequence;

    @Override
    public int getConvertFromIndex() {
      return convertFromIndex;
    }

    @Override
    public int[] getConvertToCodePointSequence() {
      return convertToCodePointSequence;
    }

    private void addTreeNodeInPlay(final int currentParseIndex, final TreeNode treeNode) {
      convertFromIndexesForNodesInPlay.add(currentParseIndex);
      treeNodeInPlay.add(treeNode);
    }

    private void updateTreeNodeInPlay(final int index, final TreeNode treeNode) {
      treeNodeInPlay.set(index, treeNode);
    }

    private void removeTreeNodeInPlay(final int index) {
      convertFromIndexesForNodesInPlay.remove(index);
      treeNodeInPlay.remove(index);
    }

    private TreeNode getTreeNodeInPlay(final int index) {
      return treeNodeInPlay.get(index);
    }

    private int getConvertFromIndexForNodeInPlay(final int index) {
      return convertFromIndexesForNodesInPlay.get(index);
    }

    private int treeNodesInPlaySize() {
      return treeNodeInPlay.size();
    }
  }

  /**
   * A tree-node for an n-ary tree.
   *
   * <pre>{@code
   *   ┌─────────────────────────────────────────────┐
   *   │ TreeNode                                    │←─┐
   *   ├─────────────────────────────────────────────┤  │ map of int codePoint keys to TreeNode values
   *   │ nodesByCodePoint : Map‹codePoint, TreeNode› │──┘
   *   │ toSequence       : int[]                    │──────→ int[] codePoints
   *   └─────────────────────────────────────────────┘
   * }</pre>
   */
  static class TreeNode {

    private final PrimitiveHashMapOfIntKeyToObjectValue<TreeNode> nodesByCodePoint;
    private int[] toCodePointSequence;

    public TreeNode() {
      this(null);
    }

    public TreeNode(final int[] toCodePointSequence) {
      this.toCodePointSequence = toCodePointSequence;
      this.nodesByCodePoint = new PrimitiveHashMapOfIntKeyToObjectValue<>();
    }

    void put(final int codePoint, final TreeNode treeNode) {
      nodesByCodePoint.put(codePoint, treeNode);
    }

    TreeNode get(final int codePoint) {
      return nodesByCodePoint.get(codePoint);
    }

    int[] codePoints() {
      return nodesByCodePoint.keySet();
    }

    boolean hasToSequence() {
      return toCodePointSequence != null;
    }

    boolean isLeafNode() {
      return nodesByCodePoint.isEmpty();
    }

    @Override
    public String toString() {
      final StringBuilder s = new StringBuilder();
      final Deque<TreeNode> treeNodeStack = new ArrayDeque<>();
      final Deque<Integer> indexStack = new ArrayDeque<>();
      final Deque<String> indentLevels = new ArrayDeque<>();
      indentLevels.push("");
      TreeNode currentTreeNode = this;
      int[] codePoints = currentTreeNode.codePoints();
      int i = 0;
      s.append("•").append(SYSTEM_LINE_SEPARATOR);
      while (i < codePoints.length) {
        int codePoint = codePoints[i];
        indentLevels.descendingIterator().forEachRemaining(s::append);
        if (i < codePoints.length - 1) {
          s.append('├');
        } else {
          s.append('└');
        }
        s.appendCodePoint(codePoint);
        final TreeNode treeNode = currentTreeNode.get(codePoint);
        if (treeNode != null) {
          if (treeNode.hasToSequence()) {
            s.append(" ⟶ \"");
            s.append(new String(treeNode.toCodePointSequence, 0, treeNode.toCodePointSequence.length));
            s.append('"');
          }
          s.append(SYSTEM_LINE_SEPARATOR);
          if (!treeNode.isLeafNode()) {
            indentLevels.push(i < (codePoints.length - 1) ? "│" : " ");
            treeNodeStack.push(currentTreeNode);
            indexStack.push(i);
            currentTreeNode = treeNode;
            codePoints = currentTreeNode.codePoints();
            i = 0;
          } else {
            if (currentTreeNode == this) { // is root node
              ++i;
              indentLevels.pop();
            } else if ((i + 1) < codePoints.length) {
              ++i;
            } else {
              do {
                currentTreeNode = treeNodeStack.pop();
                i = indexStack.pop();
                indentLevels.pop();
                codePoints = currentTreeNode.codePoints();
                ++i;
              } while (i >= codePoints.length && !indexStack.isEmpty());
            }
          }
        }
      }
      return s.toString();
    }
  }

  static class RootTreeNode extends TreeNode {

    private int size = 0;

    private int maxToSequenceLength = 0;

    int size() {
      return size;
    }

    boolean isEmpty() {
      return size == 0;
    }

    int getMaxToSequenceLength() {
      return maxToSequenceLength;
    }

    void add(final int[] fromSequence, final int[] toSequence) {
      TreeNode currentTreeNode = this;
      for (int i = 0; i < fromSequence.length; ++i) {
        int codePoint = fromSequence[i];
        TreeNode nextTreeNode = currentTreeNode.get(codePoint);
        if (nextTreeNode == null) {
          nextTreeNode = new TreeNode();
          currentTreeNode.put(codePoint, nextTreeNode);
        }
        currentTreeNode = nextTreeNode;
      }
      if (!currentTreeNode.hasToSequence()) {
        size++;
      }
      currentTreeNode.toCodePointSequence = toSequence;
      maxToSequenceLength = Math.max(maxToSequenceLength, toSequence.length);
    }
  }
}
