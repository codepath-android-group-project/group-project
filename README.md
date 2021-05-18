# AnimeSearch

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Progress](#Progress-Gif)
1. [Wireframes](#Wireframes)
1. [Schema](#Schema)

## Overview

### Description

An app which recommends you an anime to watch through certain options that the user chooses

### App Evaluation

[Evaluation of your app across the following attributes]

- **Category:** Entertainment
- **Mobile:** Interacts with other streaming apps in your phone
- **Story:** Allows users to find new anime to watch
- **Market:** Any anime fan that doesn't know where to look
- **Habit:** Users can search for new anime when they finish or whenever they want
- **Scope:** Can expands to include some social aspects, also it can be expanded to include other media such as tv-shows, movies or even books and mangas

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] User can create account and log in
- [x]User can look at the homescreen
- [x] User can look at their profile
- [x] User can search for animes
- [ ] User can see details about what was searched including a link that redirects to the streaming service needed

**Optional Nice-to-have Stories**

- [ ] User can follow people
- [ ] User can post and share stuff about animes they are watching
- [ ] Users can comment on these posts

### 2. Screen Archetypes

- Login / Register
  - User can create account and login
- Stream
  - User can look at the homescreen
- Detail
  - User can see item details
- Profile
  - User can look at their profile
- Seach
  - User can search for a certain item

### 3. Navigation

**Tab Navigation** (Tab to Screen)

- Homescreen
- Profile
- Search

**Flow Navigation** (Screen to Screen)

- Log in screen
  - Homescreen
- Homescreen
  - Details
- Search
  - Details

## Wireframes

[Add picture of your hand sketched wireframes in this section]
<img src="https://i.ibb.co/mF2MXMK/wireframe.jpg" width=600>

## Progress Gif

<img src="https://cdn.discordapp.com/attachments/824288728121868300/839343860110327808/progress.gif">
<img src="https://media0.giphy.com/media/qy2Nzj1xUPTkv1hfDp/giphy.gif?cid=790b7611bcd036c2882d867a160b250a6c1969664cce1cdf&rid=giphy.gif&ct=g">

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema

### Models

#### anime_post

| Property    | Type   | Description          |
| ----------- | ------ | -------------------- |
| animeTitle  | String | title for the anime  |
| image       | Image  | anime poster         |
| description | String | anime description    |
| rating      | Number | anime user rating    |
| tag         | String | anime genre          |
| year        | Number | anime release year   |
| season      | String | anime release season |
| format      | String | anime format         |

#### anime_search

| Property | Type   | Description             |
| -------- | ------ | ----------------------- |
| animeId  | String | unique id for the anime |
| tag      | String | anime categories        |

#### user_profile

| Property     | Type   | Description         |
| ------------ | ------ | ------------------- |
| userId       | String | user identification |
| prodileImage | Image  | user photo          |
| userBio      | String | user description    |

### Networking

#### Existing API Endpoints

##### Anilist API

- Base URL - [https://anilist.gitbook.io/anilist-apiv2-docs/](https://anilist.gitbook.io/anilist-apiv2-docs/)

  | HTTP Verb | Endpoint     | Description            |
  | --------- | ------------ | ---------------------- |
  | `GET`     | /title       | get anime title        |
  | `GET`     | /description | get anime desicription |
  | `GET`     | /genre       | get anime genre        |
  | `GET`     | /year        | get release year       |
  | `GET`     | /season      | get release season     |
  | `GET`     | /format      | get anime format       |
