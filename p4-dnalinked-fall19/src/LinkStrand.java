/**
 * LinkStand Class
 * Ankitha Durvasula
 * Pippa Lother
 */
public class LinkStrand implements IDnaStrand{
    //Node class within LinkStrand
    private class Node{
        String info;
        Node next;

        public Node(String s){
            info = s;
            next = null;
        }
    }

    //Created Instance variables
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private Node myCurrent;
    private int myIndex;
    private int myLocalIndex;

    //default constructor
    public LinkStrand(){
        this("");
    }

    //Initialize instance variables in constructor with initialize method
    public LinkStrand(String s){
        initialize(s);
    }

    @Override
    //returns length of IDNAStrand
    public long size() {
        return mySize;
    }

    @Override
    //initializes instance variables
    public void initialize(String source) {
        myFirst = new Node(source);
        myLast = myFirst;
        myAppends = 0;
        mySize = source.length();
        myLocalIndex = 0;
        myIndex = 0;
        myCurrent = myFirst;
    }

    @Override
    //get instance of a LinkStrand object
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    //adds String value to LinkStrand by changing pointer location
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna);
        myLast.next = newNode;
        myLast = newNode;
        myAppends ++;
        mySize += dna.length();
        return this;
    }

    @Override
    //loops through IDnaStrand to reverse the pointer of each node
    public IDnaStrand reverse() {
        LinkStrand reversed = new LinkStrand();
        Node last = myLast;
        Node temp = myFirst;
        while(last != temp){
            while(temp.next != last) {
                temp = temp.next;
            }
            StringBuilder builder = new StringBuilder(last.info);
            builder.reverse();
            reversed.append(builder.toString());
            last = temp;
            temp = myFirst;
        }
        StringBuilder builder = new StringBuilder(myFirst.info);
        builder.reverse();
        reversed.append(builder.toString());
        return reversed;
    }

    @Override
    //returns however many times appends has been used
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    //returns a char based on the index in a more efficient way by checking
    //where the pointer in the list is and iterating depending on that location
    public char charAt(int index) {
        if(index<0){
            throw new IndexOutOfBoundsException(index+" is not in the range of the LinkStrand");
        }
        //check to see where pointer is in list
        if(myIndex > index){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }

        //iterate until index is reached
        while (myIndex != index) {
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= myCurrent.info.length()) {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
                if(myCurrent == null){
                    throw new IndexOutOfBoundsException(index+" is not in the range of the LinkStrand");
                }
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }

    @Override
    //writes StringBuilder toString
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(myFirst.info);
        Node temp = myFirst;
        while(temp.next != null){
            temp = temp.next;
            builder.append(temp.info);
        }
        return builder.toString();
    }
}
