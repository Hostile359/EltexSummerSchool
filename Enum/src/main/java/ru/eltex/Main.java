package ru.eltex;
import java.util.Scanner;

class Main {
    
    enum Days {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,	
        FRIDAY,
        SATURDAY,
        SUNDAY;
    }
    
	public static void main(String args[])
	{
		System.out.println("Введите день недели");
		Scanner sc = new Scanner(System.in);
		String day = sc.nextLine();
		switch(Days.valueOf(day.toUpperCase()))
		{
			case MONDAY:
				System.out.println("До субботы осталось 5 дней");
				break;
			case TUESDAY:
				System.out.println("До субботы осталось 4 дня");
				break;
			case WEDNESDAY:
				System.out.println("До субботы осталось 3 дня");
				break;
			case THURSDAY:
				System.out.println("До субботы осталось 2 дня");
				break;			
			case FRIDAY:
				System.out.println("До субботы осталось 1 день");
				break;
			case SATURDAY:
				System.out.println("Вы ввели субботу");
				break;	
			case SUNDAY:
				System.out.println("Суббота была вчера. Осталось 6 дней");
				break;	
			default:
				System.out.println("Неверный формат ввода");
		}
		
	}	
}
