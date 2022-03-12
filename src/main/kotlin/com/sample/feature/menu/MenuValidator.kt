package com.sample.feature.menu

import org.valiktor.functions.isNotNull

/**
 * Validate [MenuItem.name] and [MenuItem.createdBy]
 */
fun MenuItem.validate() {
    org.valiktor.validate(this) {
        validate(MenuItem::name).isNotNull()
        validate(MenuItem::createdBy).isNotNull()
    }
}

/**
 * Validation for update Menu
 */
fun MenuItem.validateUpdate() {
    org.valiktor.validate(this) {
        validate(MenuItem::name).isNotNull()
    }
}