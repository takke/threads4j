# threads4j

threads4j 予定地だよ

threads4j is [Threads](https://threads.net/) client for Kotlin.

# Official API Doc

https://developers.facebook.com/docs/threads

# Usage

TBD


# Implementation Progress

## Auth

- [x] Generate Url for OAuth `https://threads.net/oauth/authorize`
- [x] POST `https://graph.threads.net/oauth/access_token`
- [ ] GET `https://graph.threads.net/access_token`

## Publishing

- [ ] POST `/{threads-user-id}/threads`
- [ ] POST `/{threads-user-id}/threads_publish`
- [ ] GET `/{threads-container-id}?fields=status`

## Media Retrieval

- [ ] GET `/{threads-media-id}`

## Reply Management

- [ ] GET `/{threads-media-id}/replies`
- [ ] GET `/{threads-media-id}/conversation`
- [ ] POST `/{threads-reply-id}/manage_reply`

## User

- [ ] GET `/{threads-user-id}/threads`
- [ ] GET `/{threads-user-id}/threads_publishing_limit`
- [ ] GET `/{threads-user-id}`

## Insights

- [ ] GET `/{threads-media-id}/insights`
- [ ] GET `/{threads-user-id}/threads_insights`


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
