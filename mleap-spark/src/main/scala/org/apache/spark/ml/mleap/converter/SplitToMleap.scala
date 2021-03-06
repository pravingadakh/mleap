package org.apache.spark.ml.mleap.converter

import com.truecar.mleap.core.tree
import org.apache.spark.ml.tree.{ContinuousSplit, CategoricalSplit, Split}

/**
  * Created by hwilkins on 11/18/15.
  */
case class SplitToMleap(split: Split) {
  def toMleap: tree.Split = {
    split match {
      case split: CategoricalSplit =>
        val (isLeft, categories) = if(split.leftCategories.length >= split.rightCategories.length) {
          (true, split.leftCategories)
        } else {
          (false, split.rightCategories)
        }
        val numCategories = split.leftCategories.length + split.rightCategories.length
        tree.CategoricalSplit(split.featureIndex, numCategories, categories, isLeft)
      case split: ContinuousSplit =>
        tree.ContinuousSplit(split.featureIndex, split.threshold)
    }
  }
}
