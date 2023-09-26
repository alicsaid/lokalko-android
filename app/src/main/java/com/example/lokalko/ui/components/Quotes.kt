package com.example.lokalko.ui.components

data class InspirationalQuote(val quote: String, val author: String)

fun getRandomQuote(): InspirationalQuote {
    val randomIndex = (0 until inspirationalQuotes.size).random()
    return inspirationalQuotes[randomIndex]
}

val inspirationalQuotes = listOf(
    InspirationalQuote("The only way to do great work is to love what you do.", "Steve Jobs"),
    InspirationalQuote("Believe you can and you're halfway there.", "Theodore Roosevelt"),
    InspirationalQuote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
    InspirationalQuote("The only limit to our realization of tomorrow will be our doubts of today.", "Franklin D. Roosevelt"),
    InspirationalQuote("Don't watch the clock; do what it does. Keep going.", "Sam Levenson"),
    InspirationalQuote("The greatest glory in living lies not in never falling, but in rising every time we fall.", "Nelson Mandela"),
    InspirationalQuote("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
    InspirationalQuote("Your time is limited, don't waste it living someone else's life.", "Steve Jobs"),
    InspirationalQuote("The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.", "Helen Keller"),
    InspirationalQuote("In order to succeed, we must first believe that we can.", "Nikos Kazantzakis"),
    InspirationalQuote("You are never too old to set another goal or to dream a new dream.", "C.S. Lewis"),
    InspirationalQuote("Life is 10% what happens to us and 90% how we react to it.", "Charles R. Swindoll"),
    InspirationalQuote("Whatever you can do, or dream you can, begin it. Boldness has genius, power, and magic in it.", "Johann Wolfgang von Goethe"),
    InspirationalQuote("If you want to achieve greatness stop asking for permission.", "Anonymous"),
    InspirationalQuote("The only way to achieve the impossible is to believe it is possible.", "Charles Kingsleigh (Alice in Wonderland)"),
    InspirationalQuote("Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine.", "Roy T. Bennett"),
    InspirationalQuote("What lies behind us and what lies before us are tiny matters compared to what lies within us.", "Ralph Waldo Emerson"),
    InspirationalQuote("What you get by achieving your goals is not as important as what you become by achieving your goals.", "Zig Ziglar"),
    InspirationalQuote("The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it.", "Jordan Belfort"),
    InspirationalQuote("Life is what happens when you're busy making other plans.", "John Lennon"),
    InspirationalQuote("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
    InspirationalQuote("The only limit to our realization of tomorrow will be our doubts of today.", "Franklin D. Roosevelt"),
    InspirationalQuote("Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"),
    InspirationalQuote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
    InspirationalQuote("The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.", "Helen Keller"),
    InspirationalQuote("Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment.", "Buddha"),
    InspirationalQuote("The only way to achieve the impossible is to believe it is possible.", "Charles Kingsleigh (Alice in Wonderland)"),
    InspirationalQuote("Your time is limited, don't waste it living someone else's life.", "Steve Jobs"),
    InspirationalQuote("In order to succeed, we must first believe that we can.", "Nikos Kazantzakis"),
    InspirationalQuote("Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine.", "Roy T. Bennett"),
    InspirationalQuote("Life is 10% what happens to us and 90% how we react to it.", "Charles R. Swindoll"),
    InspirationalQuote("The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it.", "Jordan Belfort"),
    InspirationalQuote("You are never too old to set another goal or to dream a new dream.", "C.S. Lewis"),
    InspirationalQuote("Whatever you can do, or dream you can, begin it. Boldness has genius, power, and magic in it.", "Johann Wolfgang von Goethe"),
    InspirationalQuote("If you want to achieve greatness stop asking for permission.", "Anonymous"),
    InspirationalQuote("Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine.", "Roy T. Bennett"),
    InspirationalQuote("What lies behind us and what lies before us are tiny matters compared to what lies within us.", "Ralph Waldo Emerson"),
    InspirationalQuote("The only way to achieve the impossible is to believe it is possible.", "Charles Kingsleigh (Alice in Wonderland)"),
    InspirationalQuote("Life is what happens when you're busy making other plans.", "John Lennon"),
    InspirationalQuote("Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"),
    InspirationalQuote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
    InspirationalQuote("Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment.", "Buddha"),
    InspirationalQuote("You have the power to shape your own destiny.", "Anonymous"),
    InspirationalQuote("The secret of getting ahead is getting started.", "Mark Twain"),
    InspirationalQuote("The only person you are destined to become is the person you decide to be.", "Ralph Waldo Emerson"),
    InspirationalQuote("The only thing standing between you and your goal is the story you keep telling yourself.", "Tony Robbins"),
    InspirationalQuote("Believe in yourself and all that you are. Know that there is something inside you that is greater than any obstacle.", "Christian D. Larson"),
    InspirationalQuote("Your time is limited, so don't waste it living someone else's life.", "Steve Jobs"),
    InspirationalQuote("The future depends on what you do today.", "Mahatma Gandhi"),
    InspirationalQuote("Dream big and dare to fail.", "Norman Vaughan"),
    InspirationalQuote("The best way to predict the future is to create it.", "Peter Drucker"),
    InspirationalQuote("The harder you work for something, the greater you'll feel when you achieve it.", "Anonymous"),
    InspirationalQuote("Don't be pushed around by the fears in your mind. Be led by the dreams in your heart.", "Roy T. Bennett"),
    InspirationalQuote("The only way to achieve the impossible is to believe it is possible.", "Charles Kingsleigh"),
    InspirationalQuote("You miss 100% of the shots you don't take.", "Wayne Gretzky"),
    InspirationalQuote("Don't count the days, make the days count.", "Muhammad Ali"),
    InspirationalQuote("Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful.", "Albert Schweitzer")
)


