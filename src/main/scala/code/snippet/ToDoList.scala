package code 
package snippet 

import net.liftweb._
import http._

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

import code.model.ToDoListItem

class ToDo {

  def addItem = "name=add-item" #> SHtml.onSubmit(title => ToDoListItem.createNew(title))

  def toDoItems = ToDoListItem.findToDo match {
    case Nil => "ul" #> fallbackText
    case items => "ul li *" #> items.map(renderItem)
  }

  def doneItems = ToDoListItem.findDone match {
    case Nil => "ul" #> fallbackText
    case items => "ul li *" #> items.map(renderItem)
  }

  def renderItem(item: ToDoListItem) = {
    ".title" #> renderTitle(item) &
        ".buttons" #> renderButtons(item) &
        ".createdate" #> item.createDate &
        ".donedate" #> item.doneDate
  }

  def renderTitle(item: ToDoListItem): NodeSeq = <b>{ item.title }</b>

  def renderButtons(item: ToDoListItem): NodeSeq = {
    if (item.done.get)
      SHtml.button("Uncheck!", () => item.unCheckItem, ("class", "btn btn-info")) ++ Text(" ") ++
      SHtml.button("Remove!", () => item.delete_!, ("class", "btn btn-danger"))
    else
      SHtml.button("Check!", () => item.checkItem, ("class", "btn btn-default"))
  }

  def fallbackText = <i>No items in this list</i>
}


