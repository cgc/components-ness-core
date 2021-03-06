/**
 * Copyright (C) 2012 Ness Computing, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nesscomputing.types;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.inject.TypeLiteral;

public class TestPlatformId
{
    @Test
    public void testBasic()
    {
        final UUID uuid = UUID.randomUUID();

        final PlatformId<User> userId = PlatformId.fromUuid(uuid);
        Assert.assertNotNull(userId);
        Assert.assertEquals(uuid, userId.getId());

        final PlatformId<User> userId2 = PlatformId.fromUuid(uuid);

        Assert.assertEquals(userId, userId2);
    }

    @Test
    public void testNull()
    {
        Assert.assertNull(StringId.valueOf(null));
    }

    @Test
    public void testDifferent()
    {
        final UUID uuid = UUID.randomUUID();
        final UUID uuid2 = UUID.randomUUID();

        final PlatformId<User> userId = PlatformId.fromUuid(uuid);
        final PlatformId<User> userId2 = PlatformId.fromUuid(uuid2);
        Assert.assertFalse(uuid.equals(uuid2));
        Assert.assertFalse(userId.equals(userId2));
        Assert.assertFalse(userId.getId().equals(userId2.getId()));
    }

    @Test
    public void testMultitypes()
    {
        final TypeLiteral<PlatformId<User>> t1 = new TypeLiteral<PlatformId<User>>() {};
        final TypeLiteral<PlatformId<Place>> t2 = new TypeLiteral<PlatformId<Place>>() {};

        Assert.assertFalse(t1.equals(t2));
    }

    @Test
    public void testBytes()
    {
        final UUID uuid = UUID.randomUUID();
        final PlatformId<User> p1 = PlatformId.fromUuid(uuid);
        final Function<PlatformId<User>, byte[]> f1 = PlatformId.toBytesFunction();
        final Function<byte [], PlatformId<User>> f2 = PlatformId.fromBytesFunction();
        final PlatformId<User> p2 = f2.apply(f1.apply(p1));
        Assert.assertEquals(p1, p2);
    }

    @Test
    public void testString()
    {
        final UUID uuid = UUID.randomUUID();
        final PlatformId<User> p1 = PlatformId.fromUuid(uuid);
        final Function<PlatformId<User>, String> f1 = PlatformId.toStringFunction();
        final Function<String, PlatformId<User>> f2 = PlatformId.fromStringFunction();
        final PlatformId<User> p2 = f2.apply(f1.apply(p1));
        Assert.assertEquals(p1, p2);
    }

    @Test
    public void testUUID()
    {
        final UUID uuid = UUID.randomUUID();
        final PlatformId<User> p1 = PlatformId.fromUuid(uuid);
        final Function<PlatformId<User>, UUID> f1 = PlatformId.toUuidFunction();
        final Function<UUID, PlatformId<User>> f2 = PlatformId.fromUuidFunction();
        final PlatformId<User> p2 = f2.apply(f1.apply(p1));
        Assert.assertEquals(p1, p2);
    }

    @Test
    public void testBytesNull()
    {
        final PlatformId<User> p1 = null;
        final Function<PlatformId<User>, byte[]> f1 = PlatformId.toBytesFunction();
        final Function<byte [], PlatformId<User>> f2 = PlatformId.fromBytesFunction();
        final byte [] value = f1.apply(p1);
        Assert.assertNull(value);
        final PlatformId<User> p2 = f2.apply(value);
        Assert.assertNull(p2);
    }

    @Test
    public void testStringNull()
    {
        final PlatformId<User> p1 = null;
        final Function<PlatformId<User>, String> f1 = PlatformId.toStringFunction();
        final Function<String, PlatformId<User>> f2 = PlatformId.fromStringFunction();
        final String value = f1.apply(p1);
        Assert.assertNull(value);
        final PlatformId<User> p2 = f2.apply(value);
        Assert.assertNull(p2);
    }

    @Test
    public void testUUIDNull()
    {
        final PlatformId<User> p1 = null;
        final Function<PlatformId<User>, UUID> f1 = PlatformId.toUuidFunction();
        final Function<UUID, PlatformId<User>> f2 = PlatformId.fromUuidFunction();
        final UUID value = f1.apply(p1);
        Assert.assertNull(value);
        final PlatformId<User> p2 = f2.apply(value);
        Assert.assertNull(p2);
    }


    public static interface User
    {
    }

    public static interface Place
    {
    }
}


