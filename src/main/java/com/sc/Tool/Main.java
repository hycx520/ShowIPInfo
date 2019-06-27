package com.sc.Tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Main extends Thread{
	public static void main(String[] args) {
		Thread th = new Main();
		Thread th1 = new Main();
		Thread th2 = new Main();
		Thread th3 = new Main();
		th.start();
		th1.start();
		th2.start();
		th3.start();
	}
	
	@Override
	public void run(){
		Random rd = new Random();
		System.out.println(Thread.currentThread().getName());
		boolean flag = true;
		BigDecimal times = new BigDecimal(0);
		BigDecimal six = times,five=times,four=times,three=times,two=times,one=times,zero=times;
		while(flag){
			int[] bonus = getBonus(rd);
			int[] ticket = getBonus(rd);
			int result = equalsBonus(bonus, ticket);
			switch (result) {
			case 6:
				six = six.add(BigDecimal.valueOf(1));
				break;
			case 5:
				five = five.add(BigDecimal.valueOf(1));
				break;
			case 4:
				four = four.add(BigDecimal.valueOf(1));
			case 3:
				three = three.add(BigDecimal.valueOf(1));
				break;
			case 2:
				System.out.println(Thread.currentThread().getName()+"每期双色球都买"+"，就这样"+times.divide(BigDecimal.valueOf(1560000),2,RoundingMode.HALF_UP)+"万年过去啦"
						+ "终于中了二等奖");
				two = two.add(BigDecimal.valueOf(1));
				break;
			case 1:
				one = one.add(BigDecimal.valueOf(1));
				break;
			default:
				zero = zero.add(BigDecimal.valueOf(1));
				break;
			}
			times = times.add(BigDecimal.valueOf(1));
			if(one.equals(BigDecimal.valueOf(1))){
				flag=false;
			}
		}
		System.out.println(Thread.currentThread().getName()+"每期双色球都买"
+"，就这样"+times.divide(BigDecimal.valueOf(156),2,RoundingMode.HALF_UP)+"年过去啦"
				+ "，小明终于中了一等奖");
		System.out.println("一等奖次数:"+one);
		System.out.println("二等奖次数:"+two);
		System.out.println("三等奖次数:"+three);
		System.out.println("四等奖次数:"+four);
		System.out.println("五等奖次数:"+five);
		System.out.println("六等奖次数:"+six);
		System.out.println("没中奖次数:"+zero);
		BigDecimal money = times.multiply(BigDecimal.valueOf(2));
		BigDecimal bonusMoney = one.multiply(BigDecimal.valueOf(6000000))
				.add(two.multiply(BigDecimal.valueOf(200000)))
				.add(three.multiply(BigDecimal.valueOf(300)))
				.add(four.multiply(BigDecimal.valueOf(200)))
				.add(five.multiply(BigDecimal.valueOf(10)))
				.add(six.multiply(BigDecimal.valueOf(5)));
		System.out.println(bonusMoney.subtract(money));
	}
	
	//获取单个红球，nextInt(33)是从0-32伪随机取一个数字，+1就是1-33之间取。
	public static synchronized Integer getRedOne(Random rd){
		int x = rd.nextInt(33)+1;
		return x;
	}
	//获取单个蓝球
	public static synchronized Integer getBuleOne(Random rd){
		int x = rd.nextInt(16)+1;
		return x;
	}
	//获取一串号码
	public static  int[]  getBonus(Random rd){
		int[] bonus = {0,0,0,0,0,0,0};
		for(int index=0;index<=5;index++){
			bonus[index]=getRedOne(rd);
		}
		bonus[6] = getBuleOne(rd);
		return bonus;
	}
	//对奖
	public static int equalsBonus(int[] bonus,int[] ticket){
		Boolean flag = false;
		//这里我先把蓝球单独取出来对比
		int b = bonus[6];
		int t = ticket[6];
		if(b==t){
			flag = true;
		}
		int sum = 0;
		for(int index=0;index<6;index++){
			for(int j=0;j<6;j++){
				if(bonus[index]==ticket[j]){
					ticket[j]=0;
					sum += 1;
					break;
				}
			}

		}
                //然后该是几等奖就返回数字几
		if(flag && sum==6){
			return 1;
		}
		if(sum == 6){
			return 2;
		}
		if(flag && sum==5){
			return 3;
		}
		if(sum==5 || (sum==4&&flag)){
			return 4;
		}
		if(sum==4 || (sum==3&&flag)){
			return 5;
		}
		if(flag){
			return 6;
		}
		return 0;
	}
}