@file: JvmName("ToolByteArrayList")
@file:Suppress("unused")

package com.zhuzichu.shared.tool

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * desc
 * author: 朱子楚
 * time: 2020/7/27 10:32 AM
 * since: v 1.0.0
 */

fun ArrayList<Byte>.toHexString(hasSpace: Boolean = true) = this.joinToString("") {
    (it.toInt() and 0xFF).toString(16).padStart(2, '0')
        .toUpperCase(Locale.ROOT) + if (hasSpace) " " else ""
}

fun ArrayList<Byte>.toAsciiString(hasSpace: Boolean = false) =
    this.map { it.toChar() }.joinToString(if (hasSpace) " " else "")

fun ArrayList<Byte>.readInt8(offset: Int = 0): Int {
    throwOffsetError(this, offset, 1)
    return this[offset].toInt()
}

fun ArrayList<Byte>.readUInt8(offset: Int = 0): Int {
    throwOffsetError(this, offset, 1)
    return this[offset].toInt() and 0xFF
}

fun ArrayList<Byte>.readInt16BE(offset: Int = 0): Int {
    throwOffsetError(this, offset, 2)
    return (this[offset].toInt() shl 8) + (this[offset + 1].toInt() and 0xFF)
}

fun ArrayList<Byte>.readUInt16BE(offset: Int = 0): Int {
    throwOffsetError(this, offset, 2)
    return ((this[offset].toInt() and 0xFF) shl 8) or (this[offset + 1].toInt() and 0xFF)
}


fun ArrayList<Byte>.readInt16LE(offset: Int = 0): Int {
    throwOffsetError(this, offset, 2)
    return (this[offset + 1].toInt() shl 8) + (this[offset].toInt() and 0xFF)
}

fun ArrayList<Byte>.readUInt16LE(offset: Int = 0): Int {
    throwOffsetError(this, offset, 2)
    return ((this[offset + 1].toInt() and 0xFF) shl 8) or (this[offset].toInt() and 0xFF)
}

fun ArrayList<Byte>.readInt32BE(offset: Int = 0): Int {
    throwOffsetError(this, offset, 4)
    return (this[offset].toInt()) shl 24 or
            ((this[offset + 1].toInt() and 0xFF) shl 16) or
            ((this[offset + 2].toInt() and 0xFF) shl 8) or
            (this[offset + 3].toInt() and 0xFF)
}

fun ArrayList<Byte>.readUInt32BE(offset: Int = 0): Long {
    throwOffsetError(this, offset, 4)
    return (((this[offset].toInt() and 0xFF).toLong() shl 24) or
            ((this[offset + 1].toInt() and 0xFF).toLong() shl 16) or
            ((this[offset + 2].toInt() and 0xFF).toLong() shl 8) or
            (this[offset + 3].toInt() and 0xFF).toLong())
}

fun ArrayList<Byte>.readInt32LE(offset: Int = 0): Int {
    throwOffsetError(this, offset, 4)
    return (this[offset + 3].toInt()) shl 24 or
            ((this[offset + 2].toInt() and 0xFF) shl 16) or
            ((this[offset + 1].toInt() and 0xFF) shl 8) or
            (this[offset].toInt() and 0xFF)
}

fun ArrayList<Byte>.readUInt32LE(offset: Int = 0): Long {
    throwOffsetError(this, offset, 4)
    return (((this[offset + 3].toInt() and 0xFF).toLong() shl 24) or
            ((this[offset + 2].toInt() and 0xFF).toLong() shl 16) or
            ((this[offset + 1].toInt() and 0xFF).toLong() shl 8) or
            (this[offset].toInt() and 0xFF).toLong())
}

fun ArrayList<Byte>.readFloatBE(offset: Int = 0) =
    java.lang.Float.intBitsToFloat(this.readInt32BE(offset))

fun ArrayList<Byte>.readFloatLE(offset: Int = 0) =
    java.lang.Float.intBitsToFloat(this.readInt32LE(offset))

@SuppressLint("SimpleDateFormat")
fun ArrayList<Byte>.readTimeBE(offset: Int = 0, pattern: String = "yyyy-MM-dd HH:mm:ss") =
    SimpleDateFormat(pattern).format(this.readUInt32BE(offset) * 1000)

@SuppressLint("SimpleDateFormat")
fun ArrayList<Byte>.readTimeLE(offset: Int = 0, pattern: String = "yyyy-MM-dd HH:mm:ss") =
    SimpleDateFormat(pattern).format(this.readUInt32LE(offset) * 1000)

fun ArrayList<Byte>.readByteArrayBE(offset: Int, byteLength: Int): ArrayList<Byte> {
    throwOffsetError(this, offset)
    return this.subList(
        offset,
        if ((offset + byteLength) > this.size) this.size else offset + byteLength
    ) as ArrayList<Byte>
}

fun ArrayList<Byte>.readByteArrayLE(offset: Int, byteLength: Int): ArrayList<Byte> {
    throwOffsetError(this, offset)
    return this.readByteArrayBE(offset, byteLength).reversed() as ArrayList<Byte>
}

fun ArrayList<Byte>.readStringBE(
    offset: Int,
    byteLength: Int,
    encoding: String = "hex",
    hasSpace: Boolean = encoding.toLowerCase(Locale.ROOT) == "hex"
): String {
    return when (encoding.toLowerCase(Locale.ROOT)) {
        "hex" -> this.readByteArrayBE(offset, byteLength).toHexString(hasSpace)
        "ascii" -> this.readByteArrayBE(offset, byteLength).map { it.toChar() }
            .joinToString(if (hasSpace) " " else "")
        else -> ""
    }
}

fun ArrayList<Byte>.readStringLE(
    offset: Int,
    byteLength: Int,
    encoding: String = "hex",
    hasSpace: Boolean = encoding.toLowerCase(Locale.ROOT) == "hex"
): String {
    return when (encoding.toLowerCase(Locale.ROOT)) {
        "hex" -> this.readByteArrayLE(offset, byteLength).toHexString(hasSpace)
        "ascii" -> this.readByteArrayLE(offset, byteLength).map { it.toChar() }
            .joinToString(if (hasSpace) " " else "")
        else -> ""
    }
}

fun ArrayList<Byte>.writeInt8(value: Int, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset)
    this[offset] = value.toByte()
    return this
}

fun ArrayList<Byte>.writeInt16BE(value: Int, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset, 2)
    this[offset] = (value and 0xff00 ushr 8).toByte()
    this[offset + 1] = (value and 0xff).toByte()
    return this
}

fun ArrayList<Byte>.writeInt16LE(value: Int, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset, 2)
    this[offset] = (value and 0xff).toByte()
    this[offset + 1] = (value and 0xff00 ushr 8).toByte()
    return this
}

fun ArrayList<Byte>.writeInt32BE(value: Long, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset, 4)
    this[offset + 3] = (value and 0xff).toByte()
    this[offset + 2] = (value and 0xff00 ushr 8).toByte()
    this[offset + 1] = (value and 0xff0000 ushr 16).toByte()
    this[offset] = (value and 0xff000000 ushr 24).toByte()
    return this
}

fun ArrayList<Byte>.writeInt32LE(value: Long, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset, 4)
    this[offset] = (value and 0xff).toByte()
    this[offset + 1] = (value and 0xff00 ushr 8).toByte()
    this[offset + 2] = (value and 0xff0000 ushr 16).toByte()
    this[offset + 3] = (value and 0xff000000 ushr 24).toByte()
    return this
}

fun ArrayList<Byte>.writeFloatBE(value: Float, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset, 4)
    this.writeInt32BE(java.lang.Float.floatToIntBits(value).toLong(), offset)
    return this
}

fun ArrayList<Byte>.writeFloatLE(value: Float, offset: Int = 0): ArrayList<Byte> {
    throwOffsetError(this, offset, 4)
    this.writeInt32LE(java.lang.Float.floatToIntBits(value).toLong(), offset)
    return this
}

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun ArrayList<Byte>.writeTimeBE(
    time: String,
    offset: Int = 0,
    pattern: String = "yyyy-MM-dd HH:mm:ss"
): ArrayList<Byte> {
    this.writeInt32BE(SimpleDateFormat(pattern).parse(time).time / 1000, offset)
    return this
}

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun ArrayList<Byte>.writeTimeLE(
    time: String,
    offset: Int = 0,
    pattern: String = "yyyy-MM-dd HH:mm:ss"
): ArrayList<Byte> {
    this.writeInt32LE(SimpleDateFormat(pattern).parse(time).time / 1000, offset)
    return this
}

fun ArrayList<Byte>.writeByteArrayBE(
    list: ArrayList<Byte>,
    offset: Int = 0,
    length: Int = list.size
): ArrayList<Byte> {
    this.writeStringBE(list.toHexString(), offset, length)
    return this
}

fun ArrayList<Byte>.writeByteArrayLE(
    list: ArrayList<Byte>,
    offset: Int = 0,
    length: Int = list.size
): ArrayList<Byte> {
    this.writeStringLE(list.toHexString(), offset, length)
    return this
}


/**
 * @str 写入的字符串
 * @encoding  Hex & ASCII
 */
fun ArrayList<Byte>.writeStringBE(
    str: String,
    offset: Int = 0,
    encoding: String = "hex"
): ArrayList<Byte> {
    throwOffsetError(this, offset)
    when (encoding.toLowerCase(Locale.ROOT)) {
        "hex" -> {
            val hex = str.replace(" ", "")
            throwHexError(hex)
            for (i in 0 until hex.length / 2) {
                if (i + offset < this.size) {
                    this[i + offset] = hex.substring(i * 2, i * 2 + 2).toInt(16).toByte()
                }
            }
        }
        "ascii" -> {
            val hex = str.toCharArray().map { it.toInt() }.map { it.toString(16) }.joinToString("")
            this.writeStringBE(hex, offset, "hex")
        }
    }
    return this
}

fun ArrayList<Byte>.writeStringLE(
    str: String,
    offset: Int = 0,
    encoding: String = "hex"
): ArrayList<Byte> {
    when (encoding.toLowerCase(Locale.ROOT)) {
        "hex" -> {
            val hex = str.reversalEvery2Charts()
            this.writeStringBE(hex, offset, encoding)
        }
        "ascii" -> {
            val hex = str.toCharArray().map { it.toInt() }.map { it.toString(16) }.joinToString("")
            this.writeStringLE(hex, offset, "hex")
        }
    }
    return this
}

fun ArrayList<Byte>.writeStringBE(
    str: String,
    offset: Int,
    length: Int,
    encoding: String = "hex"
): ArrayList<Byte> {
    throwOffsetError(this, offset, length)
    when (encoding.toLowerCase(Locale.ROOT)) {
        "hex" -> {
            val hex = str.replace(" ", "").padStart(length * 2, '0').substring(0, length * 2)
            throwHexError(hex)
            this.writeStringBE(hex, offset)
        }
        "ascii" -> {
            val hex = str.toCharArray().map { it.toInt() }.map { it.toString(16) }.joinToString("")
            this.writeStringBE(hex, offset, length, "hex")
        }
    }
    return this
}

fun ArrayList<Byte>.writeStringLE(
    str: String,
    offset: Int,
    length: Int,
    encoding: String = "hex"
): ArrayList<Byte> {
    when (encoding.toLowerCase(Locale.ROOT)) {
        "hex" -> {
            val hex = str.reversalEvery2Charts().padEnd(length * 2, '0').substring(0, length * 2)
            this.writeStringBE(hex, offset, length, encoding)
        }
        "ascii" -> {
            val hex = str.toCharArray().map { it.toInt() }.map { it.toString(16) }.joinToString("")
            this.writeStringLE(hex, offset, length, "hex")
        }
    }
    return this
}

private fun readUnsigned(
    list: ArrayList<Byte>,
    len: Int,
    offset: Int,
    littleEndian: Boolean
): Long {
    var value = 0L
    for (count in 0 until len) {
        val shift = (if (littleEndian) count else (len - 1 - count)) shl 3
        value = value or (0xff.toLong() shl shift and (list[offset + count].toLong() shl shift))
    }
    return value
}

/****  异常  ****/
private fun throwLenError(list: ArrayList<Byte>, byteLength: Int) {
    if (byteLength <= 0 || byteLength > 4) throw IllegalArgumentException("The value of \"byteLength\" is out of range. It must be >= 1 and <= 4. Received ${byteLength}")
    if (byteLength > list.size) throw IllegalArgumentException("Attempt to write outside ArrayList<Byte> bounds.")
}

private fun throwHexError(hex: String) {
    if (hex.length % 2 != 0) throw IllegalArgumentException("The value of \"hex\".length is out of range. It must be an even number")
}

private fun throwOffsetError(
    list: ArrayList<Byte>,
    offset: Int,
    length: Int = 1,
    byteLength: Int = 0
) {
    if (offset > list.size - length - byteLength) throw IllegalArgumentException("The value of \"offset\" is out of range. It must be >= 0 and <= ${list.size - length - byteLength}. Received ${offset}")
}