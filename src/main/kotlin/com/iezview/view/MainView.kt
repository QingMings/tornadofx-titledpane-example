package com.iezview.view

import com.iezview.app.Styles
import javafx.geometry.NodeOrientation
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Accordion
import javafx.scene.control.ContentDisplay
import javafx.scene.control.TitledPane
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

/**
 * 展示 使 TitledPane  箭头位置在右侧
 * 主要是 NodeOrientation 调整 节点显示顺序
 * https://wiki.openjdk.java.net/display/OpenJFX/Node+Orientation+in+JavaFX
 */
class MainView : View("Make the arrow on the right ") {
    init {
        importStylesheet(accordionStyle::class)
    }

    override val root = borderpane {
        center {
            accordion {
                foldq("按级别", "com/iezview/image/u320.gif", VBox(), expanded = false) {
                    nodeOrientation = NodeOrientation.LEFT_TO_RIGHT
                    label {
                        text = "HelloWorld"
                    }
                }
                foldq("按行政区域", "com/iezview/image/u322.gif", VBox(), expanded = false) {


                }
                foldq("按学科", "com/iezview/image/u326.gif", VBox(), expanded = false) {


                }
                foldq("按生态区", "com/iezview/image/u324.gif", VBox(), expanded = false) {


                }

            }
        }
    }
}

class accordionStyle : Stylesheet() {
    init {
        accordion {
            prefWidth = 300.px
            prefHeight = 700.px
            title {
                focusColor = Color.TRANSPARENT
            }
            backgroundColor += c("#fcfdee")
            titledPane {
                title {
                    alignment = Pos.CENTER_RIGHT
                    backgroundColor += c("#f4e5cd")
                    borderColor += box(c("#fcfdee"))
                    arrowButton {
                        prefHeight = 0.px
                        prefWidth = 0.px
                    }
                    prefHeight = 40.px
                    fontSize = 15.px
                }
                content {
                    backgroundColor += c("#fcfdee")
                }
            }
        }

    }

}

fun <T : Node> Accordion.foldq(title: String? = null, graphic: String? = null, node: T, expanded: Boolean = false, op: (T.() -> Unit)? = null): TitledPane {
    val fold = TitledPane(title, node)
    fold.isExpanded = expanded
    if (graphic != null) {
        fold.graphic = hbox {
            imageview(Image(graphic)) {
                fitHeight = 16.0
                fitWidth = 16.0
                HBox.setMargin(this, tornadofx.insets(0, 14, 0, 0))
            }
        }
        // 因为 node顺序变了，所以  contentDisplay.Right 其实显示 Icon为左边
        fold.contentDisplay = ContentDisplay.RIGHT
    }
    fold.let {
        //使TitledPane node方向 从右到左
        this.nodeOrientation = NodeOrientation.RIGHT_TO_LEFT
    }
    panes += fold
    op?.invoke(node)
    return fold
}