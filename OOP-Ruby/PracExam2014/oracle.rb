class Oracle

  def initialize
    @secret_number = (rand(100)+1)
  end

  def to_s
    str = "oracle picked the number #{@secret_number}"
  end
  def is_it(number)
    @secret_number <=> number
    # if @secret_number == number
    #   return 0
    # elsif @secret_number > number
    #   return 1
    # elsif @secret_number < number
    #   return -1
    # end
  end
end
