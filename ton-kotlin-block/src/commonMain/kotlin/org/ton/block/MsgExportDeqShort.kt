package org.ton.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ton.bitstring.BitString
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.cell.invoke
import org.ton.tlb.TlbConstructor
import org.ton.tlb.providers.TlbConstructorProvider

@Serializable
@SerialName("msg_export_deq_short")
data class MsgExportDeqShort(
    val msg_env_hash: BitString,
    val next_workchain: Int,
    val next_addr_pfx: ULong,
    val import_block_lt: ULong
) : OutMsg {
    init {
        require(msg_env_hash.size == 256) { "required: msg_env_hash.size == 256, actual: ${msg_env_hash.size}" }
    }

    companion object : TlbConstructorProvider<MsgExportDeqShort> by MsgExportDeqShortTlbConstructor
}

private object MsgExportDeqShortTlbConstructor : TlbConstructor<MsgExportDeqShort>(
    schema = "msg_export_deq_short\$1101 msg_env_hash:bits256 " +
        "next_workchain:int32 next_addr_pfx:uint64 " +
        "import_block_lt:uint64 = OutMsg;"
) {
    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: MsgExportDeqShort
    ) = cellBuilder {
        storeBits(value.msg_env_hash)
        storeInt(value.next_workchain, 32)
        storeUInt64(value.next_addr_pfx)
        storeUInt64(value.import_block_lt)
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): MsgExportDeqShort = cellSlice {
        val msgEnvHash = loadBits(256)
        val nextWorkchain = loadTinyInt(32).toInt()
        val nextAddrPfx = loadUInt64()
        val importBlockLt = loadUInt64()
        MsgExportDeqShort(msgEnvHash, nextWorkchain, nextAddrPfx, importBlockLt)
    }
}
