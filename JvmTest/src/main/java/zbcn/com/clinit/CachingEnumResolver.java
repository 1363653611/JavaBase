package zbcn.com.clinit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *  @title CachingEnumResolver
 *  https://www.ibm.com/developerworks/cn/java/j-lo-clobj-init/index.html
 *  @Description 这个类在我调整类实例化语句位置之前运行正常，但当我把该类实例化语句调整到静态初始化语句之前时，我的程序不再为我工作了
 *  @author zbcn8
 *  @Date 2019/11/22 9:43
 */
public class CachingEnumResolver {

	//单态实例　一切问题皆由此行引起
	private static final CachingEnumResolver SINGLE_ENUM_RESOLVER = new CachingEnumResolver();
	//private static final CachingEnumResolver SINGLE_ENUM_RESOLVER;
	/*MSGCODE->Category内存索引*/
	private static Map CODE_MAP_CACHE;

	static {
		CODE_MAP_CACHE = new HashMap();
		//为了说明问题,我在这里初始化一条数据
		CODE_MAP_CACHE.put("0", "北京市");
		//SINGLE_ENUM_RESOLVER = new CachingEnumResolver();
		initEnums();
	}

	//private, for single instance
	private CachingEnumResolver() {
		//初始化加载数据  引起问题，该方法也要负点责任
		//initEnums();
	}

	/**
	 * 初始化所有的枚举类型
	 *      
	 */
	public static void initEnums() {
		// ~~~~~~~~~问题从这里开始暴露 ~~~~~~~~~~~//
		if (null == CODE_MAP_CACHE) {
			System.out.println("CODE_MAP_CACHE为空,问题在这里开始暴露.");
			CODE_MAP_CACHE = new HashMap();
		}
		CODE_MAP_CACHE.put("1", "北京市");
		CODE_MAP_CACHE.put("2", "云南省");
		//..... other code...
	}

	public Map getCache() {
		return Collections.unmodifiableMap(CODE_MAP_CACHE);
	}

	/**
	 *      * 获取单态实例
	 *      *
	 *      * @return
	 *      
	 */
	public static CachingEnumResolver getInstance() {
		return SINGLE_ENUM_RESOLVER;
	}

	public static void main(String[] args) {
		System.out.println(CachingEnumResolver.getInstance().getCache());
	}
}