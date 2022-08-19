/*
 * Queue
 *
 * This class provides a simple thread-safe FIFO queue.
 *
 * Make sure the code works, is thread-safe, and is simple, clear and
 * very readable.  Please do not change any of the provided code.
 */

package Solution;

/* PS: Do not use any imports from the standard library.  Else it
 * would be way too easy using standard classes. ;-)
 */

public class Queue extends Object
{
  private Node head; /* the first item in the queue */
  private Node tail; /* the last item in the queue */

  /* Creates a new Queue object.  */
  public Queue ()
  {
    this.head = null;
    this.tail = null;
  }

  /* Puts an object at the end of the queue.   */
  public void putObject (Object object)
  {
    /* Create a new instance of Node to hold the object. */
    Node node = new Node (object);

    synchronized (this)
    {
      /* If the queue is not empty, append the new node instance 
         to the last node in the queue. */
      if (this.tail != null)
      {
        this.tail.next = node;
      }

      /* Update tail to reference the new node instance. */
      this.tail = node;

      /* If the queue was previously empty, set head to the new 
         node instance. */
      if (this.head == null)
      {
        this.head = node;
      }
    }
  }

  /* Gets an object from the beginning of the queue.  The object is
   * removed from the queue.  If there are no objects in the queue,
   * returns null.
   */
  public Object getObject ()
  {
    synchronized (this)
    {
      /* If the queue is empty, return null. */
      if (this.head == null)
      {
        return null;
      }

      /* Save the value of the object field of the current head
         instance to a temporary variable. */
      Object object = this.head.object;

      /* Update head to reference the next node. */
      this.head = this.head.next;

      /* If the queue is now empty, set tail to null. */
      if (this.head == null)
      {
        this.tail = null;
      }

      /* Return the object saved previously. */
      return object;
    }
  }

  /*
   * Node
   *
   * A private inner class to hold a reference to an object
   * along with a reference to the next node in the queue in a
   * linked list.
   *
   */
  private class Node
  {
    private final Object object;
    private Node next;

    public Node (final Object object)
    {
      this.object = object;
      this.next = null;
    }
  }
}
