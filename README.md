# threads4j

threads4j 予定地だよ

threads4j is [Threads](https://threads.net/) client for Kotlin.

# Official API Doc

https://developers.facebook.com/docs/threads

# Usage

## Get Public Timeline

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
        
val timelines = Timelines(client)
val statuses: List<Status> = timelines.getPublic().execute()
```

## Register App

If you want to access the auth required API, you need create client credential and get access token. see more [docs](https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#apps)

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
val apps = Apps(client)
val appRegistration = apps.createApp(
	clientName = "client name",
	redirectUris = "urn:ietf:wg:oauth:2.0:oob",
	scope = Scope(Scope.Name.ALL),
	website = "https://sample.com"
).execute()
save(appRegistration) // appRegistration needs to be saved.
```

AppRegistration has client id and client secret.

## OAuth login and get Access Token

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
val clientId = appRegistration.clientId
val apps = Apps(client)

val url = apps.getOAuthUrl(clientId, Scope(Scope.Name.ALL))
// url like bellow
// https://:instance_name/oauth/authorize?client_id=:client_id&redirect_uri=:redirect_uri&response_type=code&scope=read 
// open url and OAuth login and get auth code

val authCode = //...
val clientSecret = appRegistration.clientSecret
val redirectUri = appRegistration.redirectUri
val accessToken = apps.getAccessToken(
			clientId,
			clientSecret,
			redirectUri,
			authCode,
			"authorization_code"
		)
// 	accessToken needs to be saved.
```

## Get raw json

```kotlin
val client = //...
val publicMethod = Public(client)

publicMethod.getLocalPublic()
  .doOnJson { jsonString -> 
    // You can get raw json for each element.
    println(jsonString)
  }
  .execute() 
```


# Implementation Progress

## Methods

- [ ] GET `/api/v1/accounts/:id`
- [ ] GET `/api/v1/accounts/verify_credentials`

## Auth

- [ ] Generate Url for OAuth `/oauth/authorize`
- [ ] POST password authorize `/oauth/token`
- [ ] POST `/oauth/token`

# Contribution

## Reporting Issues

Found a problem? 
Want a new feature? 
First of all see if your issue or idea has already been reported. 
If don't, just open a new clear and descriptive [issues](https://github.com/takke/threads4j/issues)

# License

```
MIT License

Copyright (c) 2024 Hiroaki TAKEUCHI

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
