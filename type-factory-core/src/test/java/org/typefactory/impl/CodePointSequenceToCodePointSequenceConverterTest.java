package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.impl.Constants.LINE_SEPARATOR;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;


class CodePointSequenceToCodePointSequenceConverterTest {

  private static Logger logger = Logger.getLogger(CodePointSequenceToCodePointSequenceConverter.class.getName());

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
    logger.fine(() -> "N-Ary tree" + LINE_SEPARATOR + rootNode);

    assertThat(rootNode.isEmpty()).isFalse();
    assertThat(rootNode.codePoints()).contains('a', 'f', 'l');
    assertThat(rootNode.size()).isEqualTo(6); // 6 separate sequences

    //TODO add more assertions
  }

}
