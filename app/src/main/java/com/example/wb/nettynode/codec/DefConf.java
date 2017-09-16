package com.example.wb.nettynode.codec;

/**
 * 基础配置信息
 * 
 * @author xwood
 * @version 2016-10-15
 */
public interface DefConf {

	/**
	 * 集群根节点路径
	 */
	public final static String LOGIC_NODE_ROOT = "/logic_node_root";

	/**
	 * 集群根节点 名字
	 */
	public final static String LOGIC_NODE_ROOT_NAME = "logic_node_root";

	/**
	 * 集群子节点路径的头
	 */
	public final static String LOGIC_NODE_SUB = "/logic_node_sub_";

	/**
	 * 树配置文件的路径 保存用key
	 */
	public final static String CONF_TREE_KEY = "TreeConfigFileKey";

	/**
	 * 节点配置文件的路径 保存用key
	 */
	public final static String CONF_NODE_KEY = "NodeConfigFileKey";

	/**
	 * 缓存服务器超时时间
	 */
	public final static int JEDIS_POOL_OUT_TIME = 30;

	/**
	 * 字符集
	 */
	public final static String ENCODE_CHARSET = "UTF-8";

	/**
	 * 资源图片路径
	 */
	// 资源的基础路径
	public final static String RESOURCE_PATH = "/home/wwwroot/edu/uploads/tempfiles/resources";

	/**
	 * 
	 */
	public final static String LOGIC_NODE_TYPE_ROOT = "RootNode";

	/**
	 * 
	 */
	public final static String LOGIC_NODE_TYPE_LOGIC = "LogicNode";

	/**
	 * 命令类型定义 请求命令
	 */
	public final static int IN_COMMAND = 0;

	/**
	 * 命令类型定义 响应命令
	 */
	public final static int OUT_COMMAND = 1;
	
	/**
	 * 命令类型定义 心跳命令
	 */
	public final static int HEART_COMMAND = 2;
	
	/**
	 * 命令类型定义 消息队列命令
	 */
	public final static int MQ_COMMAND = 3;
	
	/**
	 * 命令类型定义 工作流命令
	 */
	public final static int WF_COMMAND = 4;
	
	/**
	 * 执行结果 正常类型
	 */
	public final static String RES_OK = "RES_OK";

	/**
	 * 执行结果 异常类型
	 */
	public final static String RES_NG = "RES_NG";

	/**
	 * int类型占用长度
	 */
	public final static int INT_SIZE = 4;

	/**
	 * 课表类型 0: 班级课表 1:学生课表 2:老师课表 3:学校课表 (就是班级课表的一个个展示)
	 */
	public final static int CLASS_SCHEDULE_TYPE_CLASS = 0;

	/**
	 * 课表类型 0: 班级课表 1:学生课表 2:老师课表 3:学校课表 (就是班级课表的一个个展示)
	 */
	public final static int CLASS_SCHEDULE_TYPE_STUDENT = 1;

	/**
	 * 课表类型 0: 班级课表 1:学生课表 2:老师课表 3:学校课表 (就是班级课表的一个个展示)
	 */
	public final static int CLASS_SCHEDULE_TYPE_TEACHER = 2;

	/**
	 * 课表类型 0: 班级课表 1:学生课表 2:老师课表 3:学校课表 (就是班级课表的一个个展示)
	 */
	public final static int CLASS_SCHEDULE_TYPE_SCHOOL = 3;
	
	/**
	 * 老师主修课程类型编号
	 */
	public final static int YH_TEACHER_MAJOR = 1;
	
	/**
	 * 老师辅修课程类型编号
	 */
	public final static int YH_TEACHER_MINOR = 2;
	/**
	 * 新闻管理0：已发布
	 */
	public final static int YH_PN_STATUS_NORMAL = 0;

	/**
	 * 新闻管理1：未发布
	 */
	public final static int YH_PN_STATUS_STOP = 1;
	/**
	 * 增加新闻点击量,点赞数：1
	 */
	public final static int YH_NEWS_ADD_CLICK = 1;
	/**
	 * 新闻默认图片路径
	 */
	public final static String YH_DEFAULT_IMAGE_PATH = "";
	/**
	 * 新闻默认图片名称
	 */
	public final static String YH_DEFAULT_IMAGE = "";
	
	/**
	 * 新闻访问类型: 2  点击
	 */
	public final static int YH_NEWS_HITS = 2;
	/**
	 * 新闻访问类型: 1  点赞
	 */
	public final static int YH_NEWS_PRAISE = 1;
	
	/**
	 * 数据库删除标志 1删除
	 */
	public final static int YH_DEL_FLAG_DELETE = 1;
	/**
	 * 数据库删除标志 0正常
	 */
	public final static int YH_DEL_FLAG_NORMAL = 0;
	/**
	 * 学生考勤类型 2:迟到,4:早退,8:旷课 3旷课 2迟到 1早退
	 */
	public final static int YH_ATTENDANCE_LATE = 2;

	public final static int YH_ATTENDANCE_LEAVE = 4;

	public final static int YH_ATTENDANCE_TRUANT = 8;
	/**
	 * SQL where 开关 1关,2开
	 */
	public final static int YH_CONDITION_SWITCH_ON = 2;

	public final static int YH_CONDITION_SWITCH_OFF = 1;
	/**
	 * 资源类型
	 */
	// 资源类型 教学视频
	public final static int YH_RES_TYPE_VIDEO = 1;
	// 资源类型 图片
	public final static int YH_RES_TYPE_PIC = 2;
	// 课件
	public final static int YH_RES_TYPE_PPT = 3;
	// 资源类型 教学录音
	public final static int YH_RES_TYPE_AUDIO = 4;
	// 资源类型 电子教案
	public final static int YH_RES_TYPE_LESSONPLAN = 5;
	// 资源类型 习题作业
	public final static int YH_RES_TYPE_EXERCISES = 6;
	// 资源关系类型 文档
	public final static int YH_RES_TYPE_DOCUMENT = 7;
	// 资源关系类型 配套资源
	public final static int YH_RES_BOOK_TYPE_COMPLETE = 8;
	// 资源关系类型 标准教案
	public final static int YH_RES_TYPE_STANDARDLESSONPLAN = 9;
	// 资源关系类型 动画
	public final static int YH_RES_TYPE_CARTOON = 10;
	// 资源关系类型 学科实验工具
	public final static int YH_RES_TYPE_TESTTOOL = 12;
	// 资源关系类型 exe文件
	public final static int YH_RES_TYPE_EXE = 13;
	// 资源关系类型 打包资源
	public final static int YH_RES_TYPE_PACK = 14;
	// 资源关系类型 打包资源
	public final static int YH_RES_TYPE_SMARTPEN = 15;
	// 资源关系类型 其他
	public final static int YH_RES_TYPE_OTHER = 99;

	/**
	 * 学生课后作业状态
	 */
	// 作业已完成
	public final static int YH_HOMEWORK_ISCOMPLETE = 1;
	// 作业未完成
	public final static int YH_HOMEWORK_NOTCOMPLETE = 2;
	// 已批改
	public final static int YH_HOMEWORK_ISCORRECT = 3;
	// 未批改
	public final static int YH_HOMEWORK_NOTCORRECT = 4;
	// 正在批改
	public final static int YH_HOMEWORK_TCORRECTING = 5;
	// 正在完成中
	public final static int YH_HOMEWORK_ONGOING = 6;
	/**
	 * 课后作业习题批改状态状态
	 */
	// 正确
	public final static int YH_HOMEWORK_MARK_ISRIGHT = 1;
	// 错误
	public final static int YH_HOMEWORK_MARK_NOTRIGHT = 2;
	// 未完成
	public final static int YH_HOMEWORK_MARK_NOTCOMPLETE = 3;
	// 未批改
	public final static int YH_HOMEWORK_MARK_NOTCORRECT = 4;
	
	/**
	 * 习题类型
	 */
	// 单选题
	public final static int YH_QUESTION_TYPE_SELECT = 0;
	// 简答题
	public final static int YH_QUESTION_TYPE_QA = 1;
	// 判断题
	public final static int YH_QUESTION_TYPE_TRUEFALSE = 2;
	// 多选题
	public final static int YH_QUESTION_TYPE_MULSELECT = 3;
	// 填空题
	public final static int YH_QUESTION_TYPE_INPUT = 4;
	// 解释题
	public final static int YH_QUESTION_TYPE_EXPLAIN = 5;
	// 论述题
	public final static int YH_QUESTION_TYPE_DISCUSS = 6;
	// 证明题
	public final static int YH_QUESTION_TYPE_PROVE = 7;
	// 计算题
	public final static int YH_QUESTION_TYPE_CALCULATE = 8;
	// 作图题
	public final static int YH_QUESTION_TYPE_GRAPH = 9;
	// 其他
	public final static int YH_QUESTION_TYPE_OTHER = 10;
	
	/*
	 * 课堂练习布置类型
	 */
	// 习题
	public final static int YH_CLASSEXERCISE_QUESTION = 1;
	// sym文件
	public final static int YH_CLASSEXERCISE_SYM = 2;
	
	// 批改状态
	// 已批改
	public final static int YH_CLASSEXERCISE_ISCORRECT = 2;
	// 未批改
	public final static int YH_CLASSEXERCISE_NOTCORRECT = 1;
	// 答题状态
	// 作业未答
	public final static int YH_CLASSEXERCISE_NOTSUBMIT = 1;
	// 作业提交
	public final static int YH_CLASSEXERCISE_ISSUBMIT = 2;

	// 学生 家长查看
	public final static int YH_CLASSEXERCISE_ISVIEW = 1;
	// 学生 家长未看
	public final static int YH_CLASSEXERCISE_NOTVIEW = 2;
	
	// 终端类型
	// WEB端
	public final static int YH_CLASSEXERCISE_WEB = 1;
	// 手机
	public final static int YH_CLASSEXERCISE_APP = 2;
	// 平板
	public final static int YH_CLASSEXERCISE_TABLET = 3;
	// pc端
	public final static int YH_CLASSEXERCISE_PC = 4;
	
	// 验证码状态 -- 以验证
	public final static int YH_VERIFICATION_CODE_YES = 1; 
	// 验证码状态 -- 未验证
	public final static int YH_VERIFICATION_CODE_NO = 2; 
	// 验证码类型 -- 手机
	public final static int YH_VERIFICATION_CODE_PHONE = 1; 
	// 验证码类型 -- 微信
	public final static int YH_VERIFICATION_CODE_WEIXIN = 2; 
		
		
	// 作息时间详情信息
	// 启用作息时间
	public final static int YH_TIMETABLEUSE_ENABLED = 0;
	// 禁用作息时间
	public final static int YH_TIMETABLEUSE_DISABLED = 1;
	// 排课启用
	public final static int YH_TIMETABLETYPE_ENABLED = 1;
	// 排课禁用
	public final static int YH_TIMETABLETYPE_DISABLED = 2;
	// 上课
	public final static int YH_TIMETABLEDETAIL_INCLASS = 1;
	// 休息
	public final static int YH_TIMETABLEDETAIL_REST = 2;
	// 自习
	public final static int YH_TIMETABLEDETAIL_REVIEW = 3;
	
	
	
	// 用户注册来源
	// 网页端注册
	public final static int YH_SOURCE_TYPE_WEB = 2;
	// 手机端
	public final static int YH_SOURCE_TYPE_PHONE = 4;
	// 平板端
	public final static int YH_SOURCE_TYPE_PAD = 8;
	// 一体机
	public final static int YH_SOURCE_TYPE_PC = 16;
	// 小本子
	public final static int YH_SOURCE_TYPE_NOTE = 32;
	// 易拉宝
	public final static int YH_SOURCE_TYPE_ROLL = 64;
	
	
	
	// 用户登录类型
	// 网页端
	public final static int YH_LOGIN_TYPE_WEB = 2;
	// 安卓手机端
	public final static int YH_LOGIN_TYPE_APHONE = 4;
	// 安卓平板端
	public final static int YH_LOGIN_TYPE_APAD = 8;
	// 一体机
	public final static int YH_LOGIN_TYPE_PC = 16;
	// 安卓手表
	public final static int YH_LOGIN_TYPE_AWATCH = 32;
	// 苹果手机端
	public final static int YH_LOGIN_TYPE_IPHONE = 64;
	// 苹果平板端
	public final static int YH_LOGIN_TYPE_IPAD = 128;
	
	
	
	
	
	
	
	
	
	// 用户权限
	// 校长
	public final static int YH_USER_ROLE_PRINCIPAL = 1004;
	// 教务处
	public final static int YH_USER_ROLE_DEANSOFFICE = 1006;
	// 班主任
	public final static int YH_USER_ROLE_CLASSTEACHER = 1009;
	// 老师
	public final static int YH_USER_ROLE_TEACHER = 1010;
	// 学生
	public final static int YH_USER_ROLE_STUDENT = 1011;
	// 家长
	public final static int YH_USER_ROLE_PARENT = 1012;
	
	
	
	
	
	
	
	
	
	
	
	
	// 用户登入类型
	// 1: 微信
	public final static int YH_USERINFO_WEIXIN = 1;
	// 2: QQ
	public final static int YH_USERINFO_QQ = 2;
	// 3: 微博
	public final static int YH_USERINFO_WEIBO = 3;
	

	// 云笔记正常状态 1
	public final static int YH_NOTE_STATUS_NORMAL = 1;
	
	// 云笔记回收站状态 2
	public final static int YH_NOTE_STATUS_DELETE = 2;
	
	// 云笔记结构类型 1 笔记
	public final static int YH_NOTE_TYPE_NOTE = 1;
	// 云笔记结构类型 2 笔记文件夹 
	public final static int YH_NOTE_TYPE_NOTEFOLDER = 2;
	// 云笔记结构类型 3 收藏文件夹
	public final static int YH_NOTE_TYPE_COLLECTFOLDER = 3;
	
	// 班级表 操作状态 yh_tclass
	// 启用状态
	public final static int YH_CLASS_OPERATEFLAG_ENABLED = 0;
	// 停用状态
	public final static int YH_CLASS_OPERATEFLAG_DISABLED = 2;
	// 封存状态
	public final static int YH_CLASS_OPERATEFLAG_LIMIT = 3;
	
	
	// 发送邮件类型
	// 注册激活
	public final static String YH_MAIL_TYPE_REG = "emailTemplateVerifyReg.html";
	// 忘记密码密码找回
	public final static String YH_MAIL_TYPE_FORGET = "emailTemplateVerifyForget.html";
	// 教务处审核用户加入学校通过审核
	public final static String YH_MAIL_TYPE_LEAVE_AUDIT_PASS = "emailTemplateLeaveauditPass.html";
	//教务处审核用户加入学校未通过审核
	public final static String YH_MAIL_TYPE_LEAVE_AUDIT_NO_PASS = "emailTemplateLeaveAuditNoPass.html";
	// 班主任审核家长学生通过审核
	public final static String YH_MAIL_TYPE_IVCODE_AUDIT_PASS = "emailTemplateIVCodeAuditPass.html";
	// 班主任审核家长学生通过审核
	public final static String YH_MAIL_TYPE_IVCODE_AUDIT_NO_PASS = "emailTemplateIVCodeAuditNoPass.html";

}
