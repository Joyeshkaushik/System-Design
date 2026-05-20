package IteratorPattern;
import java.util.*;
interface iterator<T>{
    boolean hasNext();
    T next();
}
interface iterable<T>{
    iterator<T> getIterator();
}
class LinkedList implements iterable<Integer>{
    public int data;
    public LinkedList next;
    public LinkedList(int data){
        this.data=data;
        this.next=null;
    }
    public iterator<Integer>  getIterator(){
        return new LinkedListIterator(this);
    }
}
 class BinaryTree implements iterable<Integer>{
    public int data;
    public BinaryTree left;
    public BinaryTree right;
    public BinaryTree(int data){
        this.data=data;
        left=null;
        right=null;
    }
     public iterator<Integer> getIterator() {
        return new BinaryTreeInorderIterator(this);
    }
 }
 class Song{
      public String title;
    public String artist;

    public Song(String t, String a) {
        title = t;
        artist = a;
    }
 }
 class Playlist implements iterable<Song> {
    public List<Song> songs = new ArrayList<>();

    public void addSong(Song s) {
        songs.add(s);
    }

    public iterator<Song> getIterator() {
        return new PlaylistIterator(songs);
    }
}
//concrete iterators
class LinkedListIterator implements iterator<Integer>{
    private LinkedList  current;
    public LinkedListIterator(LinkedList head){
        this.current=head;
    }
    public boolean hasNext(){
        return current!=null;
    }
    public Integer next(){
        int val=current.data;
        current=current.next;
        return val;
    }
}
 class BinaryTreeInorderIterator implements iterator<Integer> {
    private Deque<BinaryTree> stk = new ArrayDeque<>();

    private void pushLefts(BinaryTree node) {
        while (node != null) {
            stk.push(node);
            node = node.left;
        }
    }

    public BinaryTreeInorderIterator(BinaryTree root) {
        pushLefts(root);
    }

    public boolean hasNext() {
        return !stk.isEmpty();
    }

    public Integer next() {
        BinaryTree node = stk.pop();
        int val = node.data;
        if (node.right != null) {
            pushLefts(node.right);
        }
        return val;
    }
}

class PlaylistIterator implements iterator<Song> {
    private List<Song> vec;
    private int index = 0;

    public PlaylistIterator(List<Song> v) {
        vec = v;
    }

    public boolean hasNext() {
        return index < vec.size();
    }

    public Song next() {
        return vec.get(index++);
    }
}
public class IteratorDesignPattern {
    public static void main(String[] args) {
        //------------------------------------------------
        // LinkedList: 1 → 2 → 3
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(3);

        iterator<Integer> iterator1 = list.getIterator();

        System.out.print("LinkedList contents: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();

        //------------------------------------------------

        // BinaryTree:
        //    2
        //   / \
        //  1   3
        BinaryTree root = new BinaryTree(2);
        root.left  = new BinaryTree(1);
        root.right = new BinaryTree(3);

        iterator<Integer> iterator2 = root.getIterator();

        System.out.print("BinaryTree inorder: ");
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
        System.out.println();

        //------------------------------------------------

        // Playlist
        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Admirin You", "Karan Aujla"));
        playlist.addSong(new Song("Husn", "Anuv Jain"));

        iterator<Song> iterator3 = playlist.getIterator();

        System.out.println("Playlist songs:");
        while (iterator3.hasNext()) {
            Song s = iterator3.next();
            System.out.println("  " + s.title + " by " + s.artist);
        }
    }
}
