/*
 * Copyright 2012 Ryuji Yamashita
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package facebook4j;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @see <a href="https://developers.facebook.com/docs/reference/api/domain/">Domain - Facebook Developers</a>
 */
public class DomainMethodsTest extends FacebookTestBase {

    @Test
    public void getById() throws Exception {
        String id = "369296215699";
        Domain domain = unauthenticated.getDomain(id);
        assertThat(domain.getId(), is(id));
        assertThat(domain.getName().toLowerCase(), is("facebook.com"));
    }

    @Test
    public void getByName() throws Exception {
        String name = "www.facebook.com";
        Domain domain = unauthenticated.getDomainByName(name);
        assertThat(domain.getId(), is("369296215699"));
        assertThat(domain.getName().toLowerCase(), is("facebook.com"));
    }

    @Test
    public void getByNames() throws Exception {
        String name1 = "www.facebook.com";
        String name2 = "www.example.com";
        List<Domain> domains = unauthenticated.getDomainsByName(name1, name2);
        assertThat(domains.size(), is(2));
        assertThat(domains.get(0).getId(), is("369296215699"));
        assertThat(domains.get(0).getName().toLowerCase(), is("facebook.com"));
        assertThat(domains.get(1).getId(), is("345629607804"));
        assertThat(domains.get(1).getName(), is("example.com"));
    }
    
}
