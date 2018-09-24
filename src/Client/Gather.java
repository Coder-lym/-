package Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import bean.Environment;
import util.LogUtil;

/**
 * 读取数据文件，返回集合对象
 * @author liangyumim
 * @created 2018年9月20日 下午2:26:07
 */
public class Gather {

	public static Collection<Environment> analytic(File file) throws Exception {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		Collection<Environment> list = new ArrayList<>();
		LogUtil.LOGGER.info("正在解析数据文件...");
		while ((line = br.readLine()) != null) {
			String[] str = line.split("\\|");
			String srcId = str[0];
			String dstId = str[1];
			String devId = str[2];
			String sersorAddress = str[3];
			int count = Integer.parseInt(str[4]);
			String cmd = str[5];
			String data = str[6];
			int status = Integer.parseInt(str[7]);
			Timestamp gather = new Timestamp(Long.parseLong(str[8]));

			if (str[3].equals("16")) {
				// 温度
				float temp = (float) (((Integer.parseInt(data.substring(0, 4), 16)) * 0.00268127) - 46.85);
				Environment enTemp = new Environment(srcId, dstId, devId, sersorAddress, count, cmd, "温度", temp, status,
						gather);
				list.add(enTemp);
				// 湿度
				float hum = (float) (((Integer.parseInt(data.substring(4, 8), 16)) * 0.00190735) - 6);
				Environment enHum = new Environment(srcId, dstId, devId, sersorAddress, count, cmd, "湿度", hum, status,
						gather);
				list.add(enHum);
			} else if (str[3].equals("256")) {
				// 光照强度
				float light = Integer.parseInt(data.substring(0, 4), 16);
				Environment en = new Environment(srcId, dstId, devId, sersorAddress, count, cmd, "光照强度", light, status,
						gather);
				list.add(en);
			} else if (str[3].equals("1280")) {
				// 二氧化碳浓度
				float carb = Integer.parseInt(data.substring(0, 4), 16);
				Environment en = new Environment(srcId, dstId, devId, sersorAddress, count, cmd, "CO2", carb, status,
						gather);
				list.add(en);
			}
		}
		LogUtil.LOGGER.info("数据文件解析完毕！");
		return list;
	}

}
