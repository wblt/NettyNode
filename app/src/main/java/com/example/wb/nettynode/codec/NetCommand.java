package com.example.wb.nettynode.codec;

import java.nio.ByteBuffer;
import java.util.Arrays;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.example.wb.nettynode.codec.DefConf;

public class NetCommand {
	
//	public final static Logger log = LoggerFactory.getLogger(NetCommand.class);
	
	// 协议版本
	private int version;
	// 命令类型
	private int cmdType;
	// 命令Id 发送以后 接收端用于识别客户端
	private String cmdId ="";
	// 命令编码  C0101001
	private String cmdCode = "";
	// 命令执行结果
	private String cmdRes = DefConf.RES_OK;
	// 命令执行结果 消息 如果没有错误，就是正常的空字符串 如果有错误就错误信息
	private String cmdResMsg = "";
	// 命令数据
	private byte[] data = new byte[0];
	
	public NetCommand() {
		super();
	}
	
	public NetCommand(String cmdRes, String cmdResMsg) {
		this.cmdRes = cmdRes;
		this.cmdResMsg = cmdResMsg;
		this.data = new byte[0];
	}
	
	public NetCommand(String cmdRes, byte[] data) {
		this.cmdRes = cmdRes;
		this.cmdResMsg = "";
		if (null == data) {
			this.data = new byte[0];
		} else {
			this.data = data;
		}
	}

	public NetCommand(String cmdRes, String cmdResMsg, byte[] data) {
		super();
		this.cmdRes = cmdRes;
		this.cmdResMsg = cmdResMsg;
		if (null == data) {
			this.data = new byte[0];
		} else {
			this.data = data;
		}
	}
	
	/**
	 * 编码协议
	 * 
	 * @return
	 */
	public byte[] encodeCmd() {
	 
		try{
			// 命令数据长度
			int dataLength = 0;
			
			// 获得命令参数长度
			if (null != data && data.length > 0) {
				dataLength = data.length;
			}
			
			// 整个命令的长度
			int totleCmdLength = 
	//				DefConf.INT_SIZE+ // 整个命令长度
					DefConf.INT_SIZE+ // version
					DefConf.INT_SIZE+ // cmdType
					DefConf.INT_SIZE+ // cmdId 长度
					this.cmdId.getBytes().length+ // cmdId
					DefConf.INT_SIZE+ // cmdCode 长度
					this.cmdCode.getBytes().length+ // cmdCode
					DefConf.INT_SIZE+ // cmdRes 长度
					this.cmdRes.getBytes().length+ // cmdRes
					DefConf.INT_SIZE+ // cmdResMsg 长度
					this.cmdResMsg.getBytes().length+ // cmdResMsg
					DefConf.INT_SIZE+ // 命令数据长度的长度
					dataLength; // 命令数据长度
			
			 // 版本 
	//		 ByteBuffer cmdBuffer = ByteBuffer.allocateDirect(4+4+4+4+this.cmdCode.getBytes().length+4+dataLength);
			 ByteBuffer cmdBuffer = ByteBuffer.allocateDirect(DefConf.INT_SIZE+totleCmdLength);
			 
			 // 整个命令长度 4
			 cmdBuffer.putInt(totleCmdLength);
			 // 版本 4
			 cmdBuffer.putInt(this.version);
			 // 命令类型 4
			 cmdBuffer.putInt(this.cmdType);
			 
			 // 命令Id
			 cmdBuffer.putInt(this.cmdId.getBytes().length);
			 cmdBuffer.put(this.cmdId.getBytes());
			 
			 // 命令编码  C0101001 8
			 // CT00001001 10
			 cmdBuffer.putInt(this.cmdCode.getBytes().length);
			 cmdBuffer.put(this.cmdCode.getBytes());
			 
			 // 命令执行结果 
			 cmdBuffer.putInt(this.cmdRes.getBytes().length);
			 cmdBuffer.put(this.cmdRes.getBytes());
			 
			 // 命令执行结果消息 
			 cmdBuffer.putInt(this.cmdResMsg.getBytes().length);
			 cmdBuffer.put(this.cmdResMsg.getBytes());
			 
			 // 命令数据长度 4
			 cmdBuffer.putInt(dataLength);
			 
			 if (null != data && data.length > 0) {
				 // 命令数据
				 cmdBuffer.put(data,0,data.length);
			 }
			 cmdBuffer.flip();
			 
			 byte[] outData = new byte[cmdBuffer.remaining()];
			 cmdBuffer.get(outData, 0, outData.length);
				
			 return outData;
		} catch (Exception ex) {
			System.out.println("encodeCmd 错误:"+ this.getCmdCode());
			System.out.println("encodeCmd 错误:"+ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	 }
	
	
	
	/**
	 * 解码协议
	 * 
	 * @return
	 */
	public NetCommand decodeCmd(byte[] datas) {
	 
		 ByteBuffer cmdBuffer = ByteBuffer.wrap(datas);
		 
		 // 整个命令长度 4
		 cmdBuffer.getInt();
		 
		 // 版本
		 int _ver = cmdBuffer.getInt();
		 this.version = _ver;
		 
		 // 命令类型 4
		 int _cmdType = cmdBuffer.getInt();
		 this.cmdType = _cmdType;
		 
		 // 命令Id
		 int cmdIdLength = cmdBuffer.getInt();
		 byte[] _bCmdId = new byte[cmdIdLength];
		 for (int index = 0; index < cmdIdLength; index++) {
			 _bCmdId[index] = cmdBuffer.get(); 
		 }
		 String _cmdId = new String(_bCmdId);
		 this.cmdId = _cmdId;
		 
		 // 命令编码  C0101001 8
		 int cmdCodeLength = cmdBuffer.getInt();
		 byte[] _bCmdCode = new byte[cmdCodeLength];
		 for (int index = 0; index < cmdCodeLength; index++) {
			 _bCmdCode[index] = cmdBuffer.get(); 
		 }
		 String _cmdCode = new String(_bCmdCode);
		 this.cmdCode = _cmdCode;
		 
		 // 命令执行结果 
		 int cmdResLength = cmdBuffer.getInt();
		 byte[] _bCmdRes = new byte[cmdResLength];
		 for (int index = 0; index < cmdResLength; index++) {
			 _bCmdRes[index] = cmdBuffer.get(); 
		 }
		 String _cmdRes = new String(_bCmdRes);
		 this.cmdRes = _cmdRes;

		 // 命令执行结果消息 
		 int cmdResMsgLength = cmdBuffer.getInt();
		 byte[] _bCmdResMsg = new byte[cmdResMsgLength];
		 for (int index = 0; index < cmdResMsgLength; index++) {
			 _bCmdResMsg[index] = cmdBuffer.get(); 
		 }
		 String _cmdResMsg = new String(_bCmdResMsg);
		 this.cmdResMsg = _cmdResMsg;

		 //  命令数据长度
		 int _dataLength = cmdBuffer.getInt();

		 if (_dataLength > 0) {
			this.data = new byte[_dataLength];
			for (int index = 0; index < _dataLength; index++) {
				this.data[index] = cmdBuffer.get();
			}
		 }
		 
		 return this;
	 }

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCmdType() {
		return cmdType;
	}

	public void setCmdType(int cmdType) {
		this.cmdType = cmdType;
	}

	public String getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getCmdRes() {
		return cmdRes;
	}

	public void setCmdRes(String cmdRes) {
		this.cmdRes = cmdRes;
	}

	public String getCmdResMsg() {
		return cmdResMsg;
	}

	public void setCmdResMsg(String cmdResMsg) {
		this.cmdResMsg = cmdResMsg;
	}

	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}



	@Override
	public String toString() {
		return "NetCommand [version=" + version + "msgId=" + cmdId + ", cmdType=" + cmdType + ", cmdCode=" + cmdCode + ", cmdRes=" + cmdRes
				+ ", cmdResMsg=" + cmdResMsg + ", data=" + Arrays.toString(data) + "]";
	}

}
