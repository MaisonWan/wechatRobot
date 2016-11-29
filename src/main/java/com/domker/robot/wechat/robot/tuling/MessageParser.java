/**
 * 
 */
package com.domker.robot.wechat.robot.tuling;

import com.blade.kit.StringKit;
import com.google.gson.Gson;

/**
 * 图灵机器人消息解析类
 * 
 * @author wanlipeng 2016年11月29日
 */
public class MessageParser {
	private Gson gson = new Gson();

	/**
	 * 解析json消息
	 * 
	 * @param json
	 * @return
	 */
	public String parser(String json) {
		try {
			MessageHead head = gson.fromJson(json, MessageHead.class);
			switch (head.getCode()) {
			case 100000: // 问答
			case 200000: // 带url的问答
				return parserAsk(json);
			case 302000:// 新闻
			case 308000:// 菜谱
			default:
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 解析问答类
	 * 
	 * @param json
	 * @return
	 */
	private String parserAsk(String json) {
		MessageAsk ask = gson.fromJson(json, MessageAsk.class);
		if (StringKit.isNotBlank(ask.getUrl())) {
			return String.format("%s\n%s", ask.getText(), ask.getUrl());
		}
		return ask.getText();
	}
}