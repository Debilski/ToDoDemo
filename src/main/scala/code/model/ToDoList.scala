package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._
import net.liftweb.sitemap.Loc._
import net.liftweb.http._
import net.liftweb.http.SHtml._
import net.liftmodules.FoBoBs.mapper._

object ToDoListItem extends ToDoListItem with LongKeyedMetaMapper[ToDoListItem] {

  def createNew(title: String): ToDoListItem = {
    this.create.title(title).createDate(new java.util.Date()).doneDate(null).done(false).saveMe
  }

  def findToDo: List[ToDoListItem] = ToDoListItem.findAll(By(ToDoListItem.done, false))
  def findDone: List[ToDoListItem] = ToDoListItem.findAll(By(ToDoListItem.done, true))

}

class ToDoListItem extends LongKeyedMapper[ToDoListItem] {
  def getSingleton = ToDoListItem

  def primaryKeyField = id
  object id extends MappedLongIndex(this)
  object title extends MappedString(this, 500)
  object done extends MappedBoolean(this)
  object createDate extends MappedDateTime(this)
  object doneDate extends MappedDateTime(this)

  def checkItem = this.done(true).doneDate(new java.util.Date()).saveMe
  def unCheckItem = this.done(false).doneDate(null).saveMe
}

