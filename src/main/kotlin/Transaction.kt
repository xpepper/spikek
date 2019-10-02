import Pan.Brand.MASTERCARD
import Pan.Brand.VISA
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

fun main() {

    val t = transaction {
        amount {
            value = 100000
            currency {
                currencyCode = "USD"
            }
            exchangeRate = BigDecimal("1.192")
        }
        card {
            pan {
                number = "7111111111111111"
                brand = MASTERCARD
            }
            expirationDate = "2020-04-20"
            ccv = 378
        }
        merchant {
            id = "488"
            name = "Coop di via maranza 3"
            terminal {
                id = "99"
                name = "cassa 34"
            }
        }
    }
    println(t)
}


fun transaction(addDetails: TransactionBuilder.() -> Unit = {}): Transaction {
    return TransactionBuilder().apply(addDetails).build()
}

@DslMarker
annotation class TransactionDsl

@TransactionDsl
class TransactionBuilder {
    private var amount = Amount(BigDecimal.ZERO, Currency.getAvailableCurrencies().first(), BigDecimal.ONE)
    private var card = Card(Pan("4111111111111111", VISA), 311, LocalDate.now())
    private var merchant = Merchant("default merchantId", "default merchant name", Terminal("default terminalId", "cash register 1"))

    fun build() = Transaction(amount, card, merchant)

    fun amount(addDetails: AmountBuilder.() -> Unit = {}) {
        amount = AmountBuilder().apply(addDetails).build()
    }

    fun card(addDetails: CardBuilder.() -> Unit) {
        card = CardBuilder().apply(addDetails).build()
    }

    fun merchant(addDetails: MerchantBuilder.() -> Unit) {
        merchant = MerchantBuilder().apply(addDetails).build()
    }
}

@TransactionDsl
class AmountBuilder {
    internal var exchangeRate = BigDecimal.ONE
    internal var value: Long = 100
    private var currency = Currency.getAvailableCurrencies().first()
    fun build() = Amount(BigDecimal(value), currency, exchangeRate)

    fun currency(addDetails: CurrencyBuilder.() -> Unit) {
        val b = CurrencyBuilder()
        b.addDetails()
        currency = b.build()
    }
}


@TransactionDsl
class MerchantBuilder {
    internal var id = "default merchantId"
    internal var name = "default merchant name"
    private var terminal = Terminal("", "")

    fun build() = Merchant(id, name, terminal)

    fun terminal(addDetails: TerminalBuilder.() -> Unit) {
        val b = TerminalBuilder()
        b.addDetails()
        terminal = b.build()
    }
}

@TransactionDsl
class CardBuilder {

    internal var ccv = 111
    internal var expirationDate = LocalDate.now().plusYears(1).toString()
    private var pan = Pan("4111111111111111", MASTERCARD)
    fun build() = Card(pan, ccv, LocalDate.parse(expirationDate))

    fun pan(addDetails: PanBuilder.() -> Unit) {
        val b = PanBuilder()
        b.addDetails()
        pan = b.build()
    }
}

@TransactionDsl
class TerminalBuilder {
    internal var id = "default id"
    internal var name = "default name"

    fun build() = Terminal(id, name)
}

@TransactionDsl
class CurrencyBuilder {
    internal var currencyCode: String = "EUR"
    fun build(): Currency = Currency.getInstance(currencyCode)
}

@TransactionDsl
class PanBuilder {
    internal var number = "98761234567890"
    internal var brand = VISA
    fun build() = Pan(number, brand)
}

data class Transaction(private val amount: Amount, private val card: Card, val merchant: Merchant)
data class Amount(private val value: BigDecimal, private val currency: Currency, private val exchangeRate: BigDecimal)
data class Card(private val pan: Pan, private val ccv: Int, private val expirationDate: LocalDate)
data class Pan(private val rawPan: String, private val brand: Brand) {
    enum class Brand { VISA, MASTERCARD }
}
data class Merchant(val merchantId: String, val name: String, val terminal: Terminal)
data class Terminal(val id: String, val name: String)
