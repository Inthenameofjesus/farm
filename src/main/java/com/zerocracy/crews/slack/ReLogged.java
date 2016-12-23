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
package com.zerocracy.crews.slack;

import com.jcabi.log.Logger;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackEvent;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.zerocracy.jstk.Farm;
import java.io.IOException;

/**
 * Pass through and log.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @param <T> Type of event
 * @since 0.1
 */
final class ReLogged<T extends SlackEvent> implements Reaction<T> {

    /**
     * Reaction.
     */
    private final Reaction<T> origin;

    /**
     * Ctor.
     * @param tgt Target
     */
    ReLogged(final Reaction<T> tgt) {
        this.origin = tgt;
    }

    @Override
    public boolean react(final Farm farm, final T event,
        final SlackSession session) throws IOException {
        if (event instanceof SlackMessagePosted) {
            final SlackMessagePosted posted =
                SlackMessagePosted.class.cast(event);
            Logger.info(
                this,
                "Slack (channel=%s/%s, sub-type=%s, sender=@%s): \"%s\"",
                posted.getChannel().getId(),
                posted.getChannel().getName(),
                posted.getMessageSubType(),
                posted.getSender().getUserName(),
                posted.getMessageContent().replaceAll("\\s", " ")
            );
        } else {
            Logger.info(
                this,
                "Slack %s event: %s",
                event.getClass().getName(),
                event.toString()
            );
        }
        return this.origin.react(farm, event, session);
    }

}
