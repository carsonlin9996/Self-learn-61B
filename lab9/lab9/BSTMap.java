package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }
        if(key.compareTo(p.key) == 0){
            return p.value;
        }
        if(key.compareTo(p.key) > 0){
            return getHelper(key, p.right);
        }else {
            return getHelper(key, p.left);
        }

        //throw new UnsupportedOperationException();
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
        // throw new UnsupportedOperationException();
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    public Node putHelper(K key, V value, Node p) {

        if(p == null){
            size += 1;
            return new Node(key, value);
        }
        if(key.compareTo(p.key) == 0){
            p.value = value;
        }
        if(key.compareTo(p.key) > 0){
            p.right = putHelper(key, value, p.right);
        }
        if(key.compareTo(p.key) < 0){
            p.left = putHelper(key, value, p.left);
        }
        return p;
        //throw new UnsupportedOperationException();
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
        //throw new UnsupportedOperationException();
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
        //throw new UnsupportedOperationException();
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();

        keySetHelper(root, keyset);

        return keyset;
        //throw new UnsupportedOperationException();
    }

    private void keySetHelper(Node node, Set<K> keySet){
        if(node == null){
            return;
        }
        keySet.add(node.key);
        keySetHelper(node.left, keySet);
        keySetHelper(node.right, keySet);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V removeValue = get(key);
        if(removeValue == null){
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        return removeValue;

        //throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V removeValue = get(key);

        if(removeValue == null || removeValue != value){
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        return removeValue;
        //throw new UnsupportedOperationException();
    }

    //returns the node of the minimum child of the sub branch.
    private Node removeHelper(K key, Node node){
        //found the key that needs to be removed
        if(key.compareTo(node.key) == 0){
            //check how many child this node has.
            if(node.left == null && node.right == null){
                return null;              //"disconnect" with the right/left node of the sub-root
            }else if(node.left == null){  //with single child on the right.
                return node.right;        //returns the right node of the subtree to the sub-root.
            }else if(node.right == null){ //with single child on the left.
                return node.left;         //returns the left node of the subtree to the sub-root.
            }else {
                Node minNodeOnRight = minNode(node.right);
                V minValueOnRight = minNodeOnRight.value;
                K minKeyOnRight = minNodeOnRight.key;

                //sets the minimum value on the right to the node to be removed.
                node.key = minKeyOnRight;
                node.value = minValueOnRight;

                node.right = removeHelper(minKeyOnRight, node.right);

                return node;

            }
        }
        if(key.compareTo(node.key) > 0){
            //removeHelper(key, node.right);
            node.right = removeHelper(key, node.right);
        }else{
            //removeHelper(key, node.left);
            node.left = removeHelper(key, node.left);
        }
        return node;
    }

    //returns the minimum node of the given leftNode, e.g. root.left
    //returns the minimum node of the root subtree.
    private Node minNode(Node node){
        if(node.left == null){
            return node;
        }
        return minNode(node.left);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
        //throw new UnsupportedOperationException();
    }

    public static void main(String[] args){
        BSTMap<Character, Integer> test = new BSTMap<>();

        test.put('K', 3);
        test.put('C', 4);
        test.put('J', 5);
        test.put('Z', 6);
        test.put('A', 0);
        test.remove('C');
        //test.keySet();
    }
}
