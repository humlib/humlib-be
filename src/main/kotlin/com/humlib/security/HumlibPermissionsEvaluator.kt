package com.humlib.security

import com.humlib.model.User
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import java.io.Serializable
import java.util.*

class HumlibPermissionsEvaluator : PermissionEvaluator {
    override fun hasPermission(authentication: Authentication?, targetDomainObject: Any?, permission: Any?): Boolean {
        // TODO: Not implemented yet, no @PostAuthorize annotation used, yet
        return false
    }

    override fun hasPermission(
        authentication: Authentication?,
        targetId: Serializable?,
        targetType: String?,
        permission: Any?
    ): Boolean {
        if (authentication == null || targetId == null || targetType == null || permission == null) {
            return false
        }

        if (targetId !is UUID) {
            return false
        }

        return targetType.let { type ->
            when (type) {
                User::class.qualifiedName -> authentication.name.equals(targetId.toString())
                        && authentication.authorities.any {
                    it.authority.equals("SCOPE_$permission")
                }
                else -> false
            }
        }
    }
}
