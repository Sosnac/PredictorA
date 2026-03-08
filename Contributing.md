# Contributing to PredictorA ⚽

Thank you for your interest in contributing! We're excited to have you on board.

## Code of Conduct

Please read and follow our [Code of Conduct](CODE_OF_CONDUCT.md). We expect respectful, inclusive, constructive participation.

## How to Contribute

### Reporting Bugs
1. Search [existing issues](https://github.com/yourusername/PredictorA/issues) first
2. Open a new issue using the **Bug Report** template
3. Include steps to reproduce, expected vs actual behavior, and your environment

### Suggesting Features
1. Open an issue using the **Feature Request** template
2. Describe the use case and expected behavior
3. Wait for maintainer approval before implementing large changes

### Submitting a Pull Request

1. **Fork** the repository
2. **Create a branch**: `git checkout -b type/description`
   - `feat/add-league-filter`
   - `fix/login-crash-on-empty-email`
   - `chore/update-dependencies`
3. **Write clean code** following the style guides below
4. **Write/update tests** — maintain ≥80% coverage
5. **Lint before committing**: `npm run lint` / `./gradlew lint`
6. **Commit with Conventional Commits**: `git commit -m "feat: add league filter to predictions"`
7. **Push and open a PR** against `develop`

### Commit Message Format
```
type(scope): short description

[optional body]

[optional footer]
```
Types: `feat`, `fix`, `chore`, `docs`, `style`, `refactor`, `test`, `perf`

## Code Style

### Kotlin (Android)
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use `ktlint` — run `./gradlew ktlintCheck`
- Prefer Jetpack Compose for all new UI
- Follow MVVM + Clean Architecture patterns

### TypeScript (Backend)
- Run `npm run lint` before committing
- Use `async/await` over `.then()` chains
- All API responses must use consistent error format
- Write JSDoc for public service methods

## Development Setup

See [README.md](README.md#getting-started) for full setup instructions.

## Project Structure

See [README.md](README.md#project-structure) for directory layout.

## Questions?

Open a [Discussion](https://github.com/yourusername/PredictorA/discussions) or join our community chat.
