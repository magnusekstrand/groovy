import net.ekstrand.findacrew.domain.Role

class BootStrap {
    def init = { servletContext ->
        ['ROLE_FACEBOOK', 'ROLE_USER', 'ROLE_ADMIN'].each { roleStr ->
            if ( !Role.findByAuthority(roleStr) ) {
                new Role(authority: roleStr).save(flush: true)
            }
        }
    }
	
    def destroy = {
    }
}