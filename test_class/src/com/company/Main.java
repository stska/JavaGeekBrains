package com.company;

public class Main {

    public static void main(String[] args) {
	 Test test = new Test();
	 int [] locs = {1,5,6};
	 int [] coc = {4,5,6,1};
	 test.setLocationCells(locs);
	 test.setLocationCells(coc);
	 System.out.println(test.getLocationCells());
    }
}
