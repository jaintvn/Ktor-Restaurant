package com.sample.util

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

/**
 * To generate new hash using
 * [input] : input string to hash
 */
fun generateHash(input: String, length: Int = 32): String {
    val random = SecureRandom.getInstance("SHA1PRNG").generateSeed(length)
    val randomHash = Hex.encodeHexString(random)
    val hash = DigestUtils.sha256Hex("$randomHash$input")
    return "$randomHash:$hash"
}

/**
 * to check if password matches with hash
 */
fun isHashMatches(password: String, currentHash: String): Boolean {
    val hashCombo = currentHash.split(":")
    val randomHash = hashCombo[0]
    val hash = hashCombo[1]
    val passwordHash = DigestUtils.sha256Hex("$randomHash$password")
    return hash == passwordHash
}