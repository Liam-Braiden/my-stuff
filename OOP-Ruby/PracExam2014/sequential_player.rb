class SequentialPlayer < Player

  def initialize(player_name, oracle, range)
    super(player_name, oracle, range)
    @guess = 1
    @guesses = 1
  end

  def no_of_guesses
    @guesses/5
  end

  def guess
    @guess += 1
    @guesses += 1

    return (@guess/5)
  end
end