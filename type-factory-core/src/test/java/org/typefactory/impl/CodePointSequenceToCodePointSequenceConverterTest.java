package org.typefactory.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.typefactory.impl.Constants.LINE_SEPARATOR;

import java.util.logging.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.typefactory.LogUtils;
import org.typefactory.impl.CodePointSequenceToCodePointSequenceConverter.RootTreeNode;


class CodePointSequenceToCodePointSequenceConverterTest {

  private static Logger logger = LogUtils.getLogger(CodePointSequenceToCodePointSequenceConverter.class);

  @Test
  void visuallyCheckNAryTree() {
    final RootTreeNode rootNode = new RootTreeNode();
    rootNode.add("abc".codePoints().toArray(), "".codePoints().toArray());
    rootNode.add("abcd".codePoints().toArray(), "xyz".codePoints().toArray());
    rootNode.add("abef".codePoints().toArray(), "tuv".codePoints().toArray());
    rootNode.add("full stop".codePoints().toArray(), "period".codePoints().toArray());
    rootNode.add("a".codePoints().toArray(), "b".codePoints().toArray());
    rootNode.add("l".codePoints().toArray(), "n".codePoints().toArray());

    logger.fine(() -> "N-Ary tree size = " + rootNode.size());
    logger.fine(() -> "N-Ary tree" + LINE_SEPARATOR + rootNode);

    assertThat(rootNode.size()).isEqualTo(3); // 'a', 'f' and 'l'
  }

}
