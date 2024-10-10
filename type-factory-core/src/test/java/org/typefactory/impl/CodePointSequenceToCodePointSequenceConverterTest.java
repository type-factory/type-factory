package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.impl.Constants.SYSTEM_LINE_SEPARATOR;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.TreeNode;


class CodePointSequenceToCodePointSequenceConverterTest {

  private static final Logger logger = Logger.getLogger(CodePointSequenceToCodePointSequenceConverter.class.getName());

  @Test
  void nAryTreeIsFormedCorrectly() {
    final RootTreeNode rootNode = new RootTreeNode();
    rootNode.add("abc".codePoints().toArray(), "".codePoints().toArray());
    rootNode.add("abcd".codePoints().toArray(), "xyz".codePoints().toArray());
    rootNode.add("abef".codePoints().toArray(), "tuv".codePoints().toArray());
    rootNode.add("full stop".codePoints().toArray(), "period".codePoints().toArray());
    rootNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    rootNode.add("l".codePoints().toArray(), "n".codePoints().toArray());

    logger.fine(() -> "N-Ary tree size = " + rootNode.size());
    logger.fine(() -> "N-Ary tree" + SYSTEM_LINE_SEPARATOR + rootNode);

    assertThat(rootNode.isEmpty()).isFalse();
    assertThat(rootNode.isLeafNode()).isFalse();
    assertThat(rootNode.codePoints()).contains('a', 'f', 'l');
    assertThat(rootNode.size()).isEqualTo(6); // 6 separate sequences
    assertThat(rootNode.getMaxToSequenceLength()).isEqualTo(6);

    final TreeNode node = rootNode.get('a');
    assertThat(node.isLeafNode()).isFalse();
    assertThat(node.codePoints()).contains('b');

    assertThat(rootNode.toString()).isEqualTo("""
        •
        ├a ⟶ "b"
        │└b
        │ ├c ⟶ ""
        │ │└d ⟶ "xyz"
        │ └e
        │  └f ⟶ "tuv"
        ├f
        │└u
        │ └l
        │  └l
        │   └\s
        │    └s
        │     └t
        │      └o
        │       └p ⟶ "period"
        └l ⟶ "n"
        """);
    //TODO add more assertions
  }


}
