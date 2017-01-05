/**
 * Copyright (c) 2016 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.pm;

import com.jcabi.log.Logger;
import java.io.IOException;

/**
 * Person.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Person {

    /**
     * Name (coordinates).
     *
     * <p>The name starts with the system, where that person is present,
     * like "slack:" or "github:".
     *
     * @return Unique name of that person
     * @throws IOException If fails on I/O
     */
    String name() throws IOException;

    /**
     * Say something to him.
     * @param message Message
     * @throws IOException If fails on I/O
     */
    void say(String message) throws IOException;

    /**
     * Fake one.
     */
    final class Fake implements Person {
        @Override
        public String name() {
            return "ABCZZFE03";
        }
        @Override
        public void say(final String message) {
            Logger.info(this, "FakePerson: %s", message);
        }
    }

}