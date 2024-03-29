package main.assignment1;

import main.assignment1.impl.MyListImpl;

public interface MyAVL4Strings {

    public void insert(String element); //Insertion in AVL operation seen during the lectures.
    
    public Couple<String> partialSearch(String beginning); //See description in the assignment
    
    public MyList<MyList<String>> LevelByLevelLists(); //See description in the assignment
    
}
