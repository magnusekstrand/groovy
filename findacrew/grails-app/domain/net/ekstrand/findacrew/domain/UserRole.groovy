package net.ekstrand.findacrew.domain

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole implements Serializable {

	User appUser
	Role appRole

	boolean equals(other) {
		if (!(other instanceof UserRole)) {
			return false
		}

		other.appUser?.id == appUser?.id &&
			other.appRole?.id == appRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (appUser) builder.append(appUser.id)
		if (appRole) builder.append(appRole.id)
		builder.toHashCode()
	}

	static UserRole get(long appUserId, long appRoleId) {
		find 'from AppUserAppRole where appUser.id=:appUserId and appRole.id=:appRoleId',
			[appUserId: appUserId, appRoleId: appRoleId]
	}

	static UserRole create(User appUser, Role appRole, boolean flush = false) {
		new UserRole(appUser: appUser, appRole: appRole).save(flush: flush, insert: true)
	}

	static boolean remove(User appUser, Role appRole, boolean flush = false) {
		UserRole instance = UserRole.findByAppUserAndAppRole(appUser, appRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User appUser) {
		executeUpdate 'DELETE FROM AppUserAppRole WHERE appUser=:appUser', [appUser: appUser]
	}

	static void removeAll(Role appRole) {
		executeUpdate 'DELETE FROM AppUserAppRole WHERE appRole=:appRole', [appRole: appRole]
	}

	static mapping = {
		id composite: ['appRole', 'appUser']
		version false
	}
}
