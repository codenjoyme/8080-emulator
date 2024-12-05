process.env.TZ = "Europe/Istanbul";

module.exports = {
  testEnvironment: "jsdom",
  transform: {
    "\\.[jt]sx?$": "@swc/jest",
  },
  automock: false,
  roots: ["<rootDir>/src/main/javascript", "<rootDir>/src/test/javascript"],
  testRegex: ".*\\.test\\.js$",
  collectCoverageFrom: [
    "<rootDir>/**/(utils|helpers)/**/*.js",
    "<rootDir>/**/*.(utils|helpers).js",
  ],
  coverageDirectory: "../coverage",
  coverageThreshold: {
    global: {
      statements: 60,
      branches: 60,
      functions: 60,
      lines: 60,
    },
  }
};
