class DataManagerTest {
    @Rule @JvmField val overrideSchedulersRule = RxSchedulersOverrideRule()
    @Mock private val mockPokemonService: PokemonService? = null
    private var dataManager: DataManager? = null

    @Before
    fun setUp() {
        dataManager = DataManager(mockPokemonService)
    }

    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        val name = "charmander"
        val pokemon = TestDataFactory.makePokemon(name)
        `when`(mockPokemonService!!.getPokemon(anyString())).thenReturn(Single.just(pokemon))

        dataManager!!.getPokemon(name).test().assertComplete().assertValue(pokemon)
    }
}