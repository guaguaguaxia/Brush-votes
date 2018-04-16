package cn.kingdee.vote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

public class Brushvotes {
	public static void main(String[] args) throws Exception {
		Brushvotes ci = new Brushvotes();

		ci.RandomVote();
	}

	public void ForceCycle() throws IOException {
		int i, j, k, l;
		int m = 0;
		Map<String, String> arg = new HashMap<String, String>();
		arg.put("vid", "2");
		arg.put("itemlist", "[{\"itemList\":[239]}]");

		for (i = 1; i < 225; i++) {
			for (j = 1; j < 225; j++) {
				for (k = 1; k < 225; k++) {
					for (l = 1; l < 225; l++) {
						Connection connect = Jsoup
								.connect("http://www.wh14869310.icoc.bz/ajax/vote_h.jsp?cmd=voteItem");
						Map<String, String> header = new HashMap<String, String>();
						String ip = i + "." + j + "." + k + "." + l + "";
						Connection data = connect.data(header).data(arg)
								.header("X-Forwarded-For", ip);
						Response re = connect.execute();
						System.out.println("投票IP:" + ip);
						if (re.body().trim().contains("成功")) {
							m++;
							System.out.println(m);
						}
					}
				}
			}
		}

	}

	public void RandomVote() throws InterruptedException {
		Map<String, String> arg = new HashMap<String, String>();
		arg.put("vid", "2");
		arg.put("itemlist", "[{\"itemList\":[238]}]");
		int i = 0;
		int j = 0;
		while(true) {
			
			try {
				Thread.sleep(100);
				Connection connect = Jsoup.connect(

				"http://www.wh14869310.icoc.bz/ajax/vote_h.jsp?cmd=voteItem")
						.timeout(10000);
				Random rand = new Random();
				int randNum1 = rand.nextInt(256) + 256;
				int randNum2 = rand.nextInt(256) + 256;
				int randNum3 = rand.nextInt(256) + 256;
				int randNum4 = rand.nextInt(256) + 256;
				String ip = randNum1 + "." + randNum2 + "." + randNum3 + "."
						+ randNum4;
				Connection data = connect.data(arg).header("X-Forwarded-For",
						ip);
				Response re = connect.execute();
				System.out.println(re.body().trim());
			} catch (IOException e) {
				Thread.sleep(200000);
				j++;
			}
			i++;
			System.out.println("投票次数:" + i);
			System.out.println("失败次数:" + j);
		}

	}

	public void test() throws IOException {
		Random rand = new Random();
		int randNum1 = rand.nextInt(256);
		int randNum2 = rand.nextInt(256);
		int randNum3 = rand.nextInt(256);
		int randNum4 = rand.nextInt(256);
		String ip = randNum1 + "." + randNum2 + "." + randNum3 + "." + randNum4;
		System.out.println(ip);
	}

	private void getIp() throws IOException {
		Connection connect = Jsoup
				.connect("http://pvt.daxiangdaili.com/ip/?tid=559460292967500&num=1&operator=1");
		Response re = connect.execute();
		System.out.println(re.body());
	}
}
