/**
 * 
 */
package my.interview.infibeam;

/**
 * @author vamsi
 * 
 */
public class LRUCacheFactory {
	static public LRUCache getBasicLRUCache() {
		System.out.println("trying to create Cache");
		return new LRUCacheImpl();
	}
}
