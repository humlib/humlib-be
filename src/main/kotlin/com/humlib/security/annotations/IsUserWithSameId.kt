package com.humlib.security.annotations

import org.springframework.security.access.prepost.PreAuthorize

@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("#id.toString() == authentication.name && hasAuthority('SCOPE_human')")
annotation class IsUserWithSameId
