package pl.inder00.drop.comparators;

import java.util.Comparator;

import pl.inder00.drop.objects.User;

public class DropComparator<User> implements Comparator<User> {

	@Override
	public int compare(Object o1, Object o2) {
		User s1 = (User)o1;
		User s2 = (User)o2;
		if(((pl.inder00.drop.objects.User) s1).getXp()==((pl.inder00.drop.objects.User) s2).getXp()){
			return 0;
		} else if(((pl.inder00.drop.objects.User) s1).getXp()>=((pl.inder00.drop.objects.User) s2).getXp()){
			return 1;
		} else {
			return -1;
		}
	}

}
