package net.ekstrand.findacrew.domain

class FacebookUser {

	long uid
    String accessToken

	static belongsTo = [user: User]

	static constraints = {
		uid unique: true
	}
}
