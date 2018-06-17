package com.qiufeng.util;
import java.util.*;
public class Container extends HashSet{
	public String UniqueName;
	public String i_dont_know_whether_it_is_raining_but_you_had_better_not_toucha_my_spaghetti="just nothing.Go back.";
	public Container(){
		super();
	}
	public Container(String nam){
		super();
		UniqueName=nam;
	}
	public HashSet getContainer(){
		return this;
	}
}
