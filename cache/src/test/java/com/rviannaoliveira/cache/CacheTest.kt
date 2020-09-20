import com.rviannaoliveira.cache.Shared.cacheModelTest
import com.rviannaoliveira.cache.Cache
import com.rviannaoliveira.cache.ICache
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CacheTest {

    companion object{
        const val CACHE_TEST = "cache_test"
    }

    private lateinit var cache : ICache

    @Before
    fun setUp(){
        cache = Cache()
    }

    @Test
    fun putCache(){
        cache.put(CACHE_TEST, cacheModelTest)
        assertEquals(cache.get(CACHE_TEST), cacheModelTest)
    }

    @Test
    fun clearOneCache(){
        cache.clearCache(CACHE_TEST)
        assertEquals(cache.get(CACHE_TEST), null)
    }

    @Test
    fun clearCache(){
        cache.clearCache()
        assertEquals(cache.get(CACHE_TEST), null)
    }
}