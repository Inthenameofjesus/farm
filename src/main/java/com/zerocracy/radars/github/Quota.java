/**
 * Copyright (c) 2016-2017 Zerocracy
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
package com.zerocracy.radars.github;

import com.jcabi.github.Github;
import com.jcabi.github.Limit;
import com.jcabi.github.Limits;
import com.zerocracy.entry.ExtGithub;
import com.zerocracy.jstk.Farm;
import java.io.IOException;

/**
 * GitHub API is over its quota.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.19
 */
public final class Quota {

    /**
     * GitHub.
     */
    private final Github github;

    /**
     * Ctor.
     * @param farm The farm
     */
    public Quota(final Farm farm) {
        this(new ExtGithub(farm).value());
    }

    /**
     * Ctor.
     * @param ghb Github
     */
    public Quota(final Github ghb) {
        this.github = ghb;
    }

    @Override
    public String toString() {
        final Limit.Smart limit = new Limit.Smart(
            this.github.limits().get(Limits.CORE)
        );
        try {
            return String.format(
                "limit=%d, remaining=%d, reset=%s",
                limit.limit(), limit.remaining(), limit.reset()
            );
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * Is it over?
     * @return TRUE if over
     * @throws IOException If fails
     */
    public boolean over() throws IOException {
        final Limit.Smart limit = new Limit.Smart(
            this.github.limits().get(Limits.CORE)
        );
        // @checkstyle MagicNumber (1 line)
        return limit.remaining() < 500;
    }

    /**
     * Is it quiet?
     * @return TRUE if we have a lot of space
     * @throws IOException If fails
     */
    public boolean quiet() throws IOException {
        final Limit.Smart limit = new Limit.Smart(
            this.github.limits().get(Limits.CORE)
        );
        // @checkstyle MagicNumber (1 line)
        return limit.remaining() > 4000;
    }

}
