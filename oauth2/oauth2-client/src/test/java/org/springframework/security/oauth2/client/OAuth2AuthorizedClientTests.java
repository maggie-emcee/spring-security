/*
 * Copyright 2002-2017 the original author or authors.
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
package org.springframework.security.oauth2.client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link OAuth2AuthorizedClient}.
 *
 * @author Joe Grandja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ClientRegistration.class)
public class OAuth2AuthorizedClientTests {
	private ClientRegistration clientRegistration;
	private String principalName;
	private OAuth2AccessToken accessToken;

	@Before
	public void setUp() {
		this.clientRegistration = mock(ClientRegistration.class);
		this.principalName = "principal";
		this.accessToken = mock(OAuth2AccessToken.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorWhenClientRegistrationIsNullThenThrowIllegalArgumentException() {
		new OAuth2AuthorizedClient(null, this.principalName, this.accessToken);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorWhenPrincipalNameIsNullThenThrowIllegalArgumentException() {
		new OAuth2AuthorizedClient(this.clientRegistration, null, this.accessToken);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorWhenAccessTokenIsNullThenThrowIllegalArgumentException() {
		new OAuth2AuthorizedClient(this.clientRegistration, this.principalName, null);
	}

	@Test
	public void constructorWhenAllParametersProvidedAndValidThenCreated() {
		OAuth2AuthorizedClient authorizedClient = new OAuth2AuthorizedClient(
			this.clientRegistration, this.principalName, this.accessToken);

		assertThat(authorizedClient.getClientRegistration()).isEqualTo(this.clientRegistration);
		assertThat(authorizedClient.getPrincipalName()).isEqualTo(this.principalName);
		assertThat(authorizedClient.getAccessToken()).isEqualTo(this.accessToken);
	}
}
