package ton.cell

import ton.bitstring.BitStringWriter

interface CellWriter : BitStringWriter {
    val cellReferences: MutableList<Cell>

    fun writeCell(cell: Cell) = apply {
        cellReferences.add(cell)
    }

    fun writeCell(cellBuilder: CellBuilder.() -> Unit) = apply {
        writeCell(buildCell(cellBuilder))
    }
}