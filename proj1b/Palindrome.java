public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        LinkedListDeque L = new LinkedListDeque();
        for(int i=0; i<word.length();i++){
            char ch = word.charAt(i);
            L.addLast(ch);
        }
        return  L;
    }

    public boolean isPalindrome(String word) {
        Deque L = wordToDeque(word);
        int end = L.size()-1;
        int start = 0;
        while(end>=start){
            if(L.get(end--)!=L.get(start++)) return false;
         }
        return true;

//        recursive way sorry I don't know how to implement this method by recursion.
//        Deque L = wordToDeque(word);

    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        int end = word.length()-1;
        int start = 0;
        while(end>=start){
            if(!cc.equalChars(word.charAt(start++),word.charAt(end--))) return false;
        }
        return true;
    }

}
