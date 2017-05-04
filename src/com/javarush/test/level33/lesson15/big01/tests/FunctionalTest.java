package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 15.11.2016.
 */
public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String string1 = Helper.generateRandomString();
        String string2 = Helper.generateRandomString();
        String string3 = string1;
        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);
        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        Assert.assertEquals(string1, shortener.getString(id1));
        Assert.assertEquals(string2, shortener.getString(id2));
        Assert.assertEquals(string3, shortener.getString(id3));
    }

    @Test
    public void testHashMapStorageStrategy() {
        StorageStrategy storageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        StorageStrategy storageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        StorageStrategy storageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        StorageStrategy storageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        StorageStrategy storageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        StorageStrategy storageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(storageStrategy);
        testStorage(shortener);
    }
}
