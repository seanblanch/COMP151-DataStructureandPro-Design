package Lab13;
/**
 * An implementation of a binary tree that will be used for constructing
 * a Huffman code.
 *
 * @author Charles Hoot
 * @modifiedBy atb
 * @version 4/23/2019
 */

import java.util.*;

public class HuffmanTree implements java.io.Serializable
{
    private BinaryNode<HuffmanCode> root;
    private BinaryNode<HuffmanCode> current;

    public HuffmanTree()
    {
        this.root = null;
        reset();
    } // end default constructor

    public HuffmanTree(HuffmanCode rootData)
    {
        this.root = new BinaryNode<>(rootData);
        reset();
    } // end constructor

    public HuffmanTree(HuffmanCode rootData, HuffmanTree leftTree,
                       HuffmanTree rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
        reset();
    } // end constructor

    private void privateSetTree(HuffmanCode rootData,
                                HuffmanTree leftTree, HuffmanTree rightTree)
    {
        this.root = new BinaryNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
            this.root.setLeftChild(leftTree.root);

        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
                this.root.setRightChild(rightTree.root);
            else
                this.root.setRightChild(rightTree.root.copy());
        } // end if

        if ((leftTree != null) && (this != leftTree))
            leftTree.clear();

        if ((rightTree != null) && (this != rightTree))
            rightTree.clear();
    } // end privateSetTree

    public HuffmanCode getRootData()
    {
        HuffmanCode rootData = null;
        if (this.root != null)
            rootData = this.root.getData();
        return rootData;
    } // end getRootData

    public boolean isEmpty()
    {
        return this.root == null;
    } // end isEmpty

    public void clear()
    {
        this.root = null;
    } // end clear


    /**
     * Get the symbol/frequency for the current node in the Huffman tree.
     *
     * @return The object of type HuffmanCode being held at the current node.
     */
    public HuffmanCode getCurrentData()
    {
        HuffmanCode result = null;

        if (this.current != null)
        {
            result = this.current.getData();
        }
        return result;
    }

    /**
     * Determine whether current node contains a single code letter.
     *
     * @return true if the current node is a leaf
     */
    public boolean isSingleSymbol()
    {
        return (this.current.getLeftChild() == null
                && this.current.getRightChild() == null);
    }

    /**
     * Moves the current node to the left child of
     * the current node.
     */
    public void advanceLeft()
    {
        this.current = this.current.getLeftChild();
    }

    /**
     * Moves the current node to the right child of
     * the current node.
     */
    public void advanceRight()
    {
        this.current = this.current.getRightChild();
    }

    /**
     * Check the node to the left of the current node to see if a symbol is there.
     *
     * @param symbol the symbol to look for
     * @return true if the symbol is on the left
     */
    public boolean checkLeft(Character symbol)
    {
        boolean result = false;
        BinaryNode<HuffmanCode> toCheck = this.current.getLeftChild();
        if (toCheck != null)
        {
            HuffmanCode sfp = toCheck.getData();
            result = sfp.inList(symbol);
        }
        return result;
    }

    /**
     * Check the node to the right of the current node to see if a symbol is there.
     *
     * @param symbol the symbol to look for
     * @return true if the symbol is on the right
     */
    public boolean checkRight(Character symbol)
    {
        boolean result = false;
        BinaryNode<HuffmanCode> toCheck = this.current.getRightChild();
        if (toCheck != null)
        {
            HuffmanCode sfp = toCheck.getData();
            result = sfp.inList(symbol);
        }
        return result;
    }

    /**
     * Sets the current node to the root of the tree.
     */
    public void reset()
    {
        this.current = this.root;
    }

    public void preOrderTraverse()
    {
        preOrderTraverse(this.root);
    }


    private void preOrderTraverse(BinaryNode<HuffmanCode> node)
    {
        if (node != null)
        {
            System.out.println(node.getData());
            preOrderTraverse(node.getLeftChild());
            preOrderTraverse(node.getRightChild());
        }
    }

    /**
     * create an inorder iterator
     * return the iterator
     */
    public Iterator<HuffmanCode> getInorderIterator()
    {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<HuffmanCode>
    {
        private Stack<BinaryNode<HuffmanCode>> nodeStack;
        private BinaryNode<HuffmanCode> currentNode;

        public InorderIterator()
        {
            nodeStack = new Stack<>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public HuffmanCode next()
        {
            BinaryNode<HuffmanCode> nextNode = null;

            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while

            if (!nodeStack.isEmpty())
            {
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
            }
            else
                throw new NoSuchElementException();

            return nextNode.getData();
        } // end next


        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end InorderIterator
}