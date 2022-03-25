package com.humlib.security.annotations

import org.springframework.security.access.prepost.PreAuthorize

@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("hasAuthority('SCOPE_kid')")
annotation class IsKid
